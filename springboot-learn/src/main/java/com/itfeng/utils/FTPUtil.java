package com.itfeng.utils;


import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;

import java.io.*;
import java.util.List;

/**
 * FTP 工具
 */
@Slf4j
public class FTPUtil {

    private FTPClient ftpClient = new FTPClient();

    private String addr, username, password, controlEncoding;
    private Integer port;

    public FTPUtil(String addr, int port, String username, String password) throws Exception {
        this.addr = addr;
        this.username = username;
        this.password = password;
        this.controlEncoding = "utf-8";
        this.port = port;
    }

    public FTPClient getFtpClient(String filePath) throws Exception {
        changeDir(filePath);
        return ftpClient;
    }

    public Boolean connectFtpServer() throws Exception {
        try {
            /**设置文件传输的编码*/
            ftpClient.setControlEncoding(controlEncoding);

            /**连接 FTP 服务器
             * 如果连接失败，则此时抛出异常，如ftp服务器服务关闭时，抛出异常：
             * java.net.ConnectException: Connection refused: connect*/
            ftpClient.connect(addr, port);
            /**登录 FTP 服务器
             * 1）如果传入的账号为空，则使用匿名登录，此时账号使用 "Anonymous"，密码为空即可*/
            Boolean bool = true;
            if (StringUtils.isBlank(username)) {
                bool = ftpClient.login("Anonymous", "");
            } else {
                bool = ftpClient.login(username, password);
            }
            if (bool == false) {
                return false;
            }

            /** 设置传输的文件类型
             * BINARY_FILE_TYPE：二进制文件类型
             * ASCII_FILE_TYPE：ASCII传输方式，这是默认的方式
             * ....
             */
            ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);

            /**
             * 确认应答状态码是否正确完成响应
             * 凡是 2开头的 isPositiveCompletion 都会返回 true，因为它底层判断是：
             * return (reply >= 200 && reply < 300);
             */
            int reply = ftpClient.getReplyCode();
            if (!FTPReply.isPositiveCompletion(reply)) {
                /**
                 * 如果 FTP 服务器响应错误 中断传输、断开连接
                 * abort：中断文件正在进行的文件传输，成功时返回 true,否则返回 false
                 * disconnect：断开与服务器的连接，并恢复默认参数值
                 */
                ftpClient.abort();
                ftpClient.disconnect();
            }
        } catch (Exception ex) {
            log.error("FTP服务器连接登录失败：{}", ex.getMessage(), ex);
            ex.printStackTrace();
            throw new Exception("FTP服务器连接登录失败：" + ex.getMessage(), ex);
        }
        return true;
    }


    /**
     * 使用完毕，应该及时关闭连接
     * 终止 ftp 传输
     * 断开 ftp 连接
     *
     * @return
     */
    public Boolean closeFTPConnect() throws Exception {
        try {
            if (ftpClient != null && ftpClient.isConnected()) {
                ftpClient.abort();
                ftpClient.disconnect();
                return true;
            }
        } catch (IOException e) {
            log.error("FTP服务关闭异常", e);
            throw new Exception("FTP服务关闭异常", e);
        }
        return false;
    }


    /**
     * 下载 FTP 服务器上指定的单个文件，而且本地存放的文件相对部分路径 会与 FTP 服务器结构保持一致
     *
     * @param absoluteLocalDirectory ：本地存储文件的绝对路径，如 E:\gxg\ftpDownload
     * @param relativeRemotePath     ：ftpFile 文件在服务器所在的绝对路径，此方法强制路径使用右斜杠"\"，如 "\video\2018.mp4"
     * @return
     */
    public void downloadSingleFile(String absoluteLocalDirectory, String relativeRemotePath) throws Exception {
        if (StringUtils.isBlank(absoluteLocalDirectory) || StringUtils.isBlank(relativeRemotePath)) {
            log.info("下载时遇到本地存储路径或者ftp服务器文件路径为空");
            throw new Exception("下载时遇到本地存储路径或者ftp服务器文件路径为空");
        }
        /**如果 FTP 连接已经关闭，或者连接无效，则重新连接*/
        if (!ftpClient.isConnected() || !ftpClient.isAvailable()) {
            connectFtpServer();
        }
        try {
            /**没有对应路径时，FTPFile[] 大小为0，不会为null*/
            FTPFile[] ftpFiles = ftpClient.listFiles(relativeRemotePath);
            FTPFile ftpFile = null;
            if (ftpFiles.length >= 1) {
                ftpFile = ftpFiles[0];
            }
            if (ftpFile != null && ftpFile.isFile()) {
                /** ftpFile.getName():获取的是文件名称，如 123.mp4
                 * 必须保证文件存放的父目录必须存在，否则 retrieveFile 保存文件时报错
                 */
                File localFile = new File(absoluteLocalDirectory, relativeRemotePath);
                if (!localFile.getParentFile().exists()) {
                    localFile.getParentFile().mkdirs();
                }
                OutputStream outputStream = new FileOutputStream(localFile);
                String workDir = relativeRemotePath.substring(0, relativeRemotePath.lastIndexOf("\\"));
                if (StringUtils.isBlank(workDir)) {
                    workDir = "/";
                }
                /**文件下载前，FTPClient工作目录必须切换到文件所在的目录，否则下载失败
                 * "/" 表示用户根目录*/
                ftpClient.changeWorkingDirectory(workDir);
                /**下载指定的 FTP 文件 到本地
                 * 1)注意只能是文件，不能直接下载整个目录
                 * 2)如果文件本地已经存在，默认会重新下载
                 * 3)下载文件之前，ftpClient 工作目录必须是下载文件所在的目录
                 * 4)下载成功返回 true，失败返回 false
                 */
                ftpClient.retrieveFile(ftpFile.getName(), outputStream);
                outputStream.flush();
                outputStream.close();
                log.info(">>>>>FTP服务器文件下载完毕*********" + ftpFile.getName());
            }
        } catch (IOException e) {
            closeFTPConnect();
            log.error("FTP文件下载异常", e);
            throw new Exception("FTP文件下载异常", e);
        }
    }


    /**
     * 遍历 FTP 服务器指定目录下的所有文件(包含子孙文件)
     *
     * @param remotePath       ：查询的 FTP 服务器目录，如果文件，则视为无效，使用绝对路径，如"/"、"/video"、"\\"、"\\video"
     * @param relativePathList ：返回查询结果，其中为服务器目录下的文件相对路径，如：\1.png、\docs\overview-tree.html 等
     * @return
     */
    public List<String> loopServerPath(String remotePath, List<String> relativePathList) throws Exception {
        /**如果 FTP 连接已经关闭，或者连接无效，则直重新连接*/
        if (!ftpClient.isConnected() || !ftpClient.isAvailable()) {
            connectFtpServer();
        }
        try {
            /**转移到FTP服务器根目录下的指定子目录
             * 1)"/"：表示用户的根目录，为空时表示不变更
             * 2)参数必须是目录，当是文件时改变路径无效
             * */
            ftpClient.changeWorkingDirectory(remotePath);
            /** listFiles：获取FtpClient连接的当前下的一级文件列表(包括子目录)
             * 1）FTPFile[] ftpFiles = ftpClient.listFiles("/docs/info");
             *      获取服务器指定目录下的子文件列表(包括子目录)，以 FTP 登录用户的根目录为基准，与 FTPClient 当前连接目录无关
             * 2）FTPFile[] ftpFiles = ftpClient.listFiles("/docs/info/springmvc.txt");
             *      获取服务器指定文件，此时如果文件存在时，则 FTPFile[] 大小为 1，就是此文件
             * */
            FTPFile[] ftpFiles = ftpClient.listFiles();
            if (ftpFiles != null && ftpFiles.length > 0) {
                for (FTPFile ftpFile : ftpFiles) {
                    if (ftpFile.isFile()) {
                        String relativeRemotePath = remotePath + "\\" + ftpFile.getName();
                        relativePathList.add(relativeRemotePath);
                    } else {
                        loopServerPath(remotePath + "\\" + ftpFile.getName(), relativePathList);
                    }
                }
            }
        } catch (IOException e) {
            closeFTPConnect();
            log.error("FTP文件下载异常", e);
            throw new Exception("FTP文件下载异常", e);
        }
        return relativePathList;
    }


    /**
     * 上传本地文件 或 目录 至 FTP 服务器----保持 FTP 服务器与本地 文件目录结构一致
     *
     * @param uploadFile 待上传的文件 或 文件夹(此时会遍历逐个上传)
     * @param filePath   文件上传路径
     * @throws Exception
     */
    public void uploadFiles(File uploadFile, String filePath) throws Exception {
        if (uploadFile == null || !uploadFile.exists()) {
            throw new FileNotFoundException("待上传文件为空或者文件不存在");
        }
        /**如果 FTP 连接已经关闭，或者连接无效，则直重新连接*/
        if (!ftpClient.isConnected() || !ftpClient.isAvailable()) {
            connectFtpServer();
        }
        try {
            if (uploadFile.isDirectory()) {
                /**如果被上传的是目录时
                 * makeDirectory：在 FTP 上创建目录(方法执行完，服务器就会创建好目录，如果目录本身已经存在，则不会再创建)
                 * 1）可以是相对路径，即不以"/"开头，相对的是 FTPClient 当前的工作路径，如 "video"、"视频" 等，会在当前工作目录进行新建目录
                 * 2）可以是绝对路径，即以"/"开头，与 FTPCLient 当前工作目录无关，如 "/images"、"/images/2018"
                 * 3）注意多级目录时，必须确保父目录存在，否则创建失败，
                 *      如 "video/201808"、"/images/2018" ，如果 父目录 video与images不存在，则创建失败
                 * */
                changeDir(filePath);
                ftpClient.makeDirectory(uploadFile.getName());
                /**变更 FTPClient 工作目录到新目录
                 * 1)不以"/"开头表示相对路径，新目录以当前工作目录为基准，即当前工作目录下不存在此新目录时，变更失败
                 * 2)参数必须是目录，当是文件时改变路径无效*/
                ftpClient.changeWorkingDirectory(filePath + uploadFile.getName());

                File[] listFiles = uploadFile.listFiles();
                for (int i = 0; i < listFiles.length; i++) {
                    File loopFile = listFiles[i];
                    if (loopFile.isDirectory()) {
                        /**如果有子目录，则迭代调用方法进行上传*/
                        uploadFiles(loopFile, filePath);
                        /**changeToParentDirectory：将 FTPClient 工作目录移到上一层
                         * 这一步细节很关键，子目录上传完成后，必须将工作目录返回上一层，否则容易导致文件上传后，目录不一致
                         * */
                        ftpClient.changeToParentDirectory();
                    } else {
                        /**如果目录中全是文件，则直接上传*/
                        FileInputStream input = new FileInputStream(loopFile);
                        ftpClient.enterLocalPassiveMode();
                        ftpClient.storeFile(loopFile.getName(), input);
                        input.close();
                        log.info(">>>>>文件上传成功****" + loopFile.getPath());
                    }
                }
            } else {
                /**如果被上传的是文件时*/
                FileInputStream input = new FileInputStream(uploadFile);
                /** storeFile:将本地文件上传到服务器
                 * 1）如果服务器已经存在此文件，则不会重新覆盖,即不会再重新上传
                 * 2）如果当前连接FTP服务器的用户没有写入的权限，则不会上传成功，但是也不会报错抛异常
                 * */
                changeDir(filePath);
                ftpClient.enterLocalPassiveMode();
                Boolean bool = ftpClient.storeFile(uploadFile.getName(), input);
                input.close();
                log.info(">>>>>文件上传成功****" + uploadFile.getPath());
            }
        } catch (IOException e) {
            closeFTPConnect();
            log.error("FTP文件上传异常", e);
            throw new Exception("FTP文件上传异常", e);
        }
    }

    /**
     * 切入或创建目录
     *
     * @param dir
     * @return
     */
    private boolean changeDir(String dir) {
        if(StringUtils.isEmpty(dir)){
            return true;
        }
        String d;
        try {
            //目录编码，解决中文路径问题
            d = new String(dir.toString().getBytes("GBK"), "iso-8859-1");
            //尝试切入目录
            if(ftpClient.changeWorkingDirectory(d)){
                return true;
            }
            dir = dir.startsWith("/") ? dir.substring(1, dir.length()) : dir;
            dir = dir.endsWith("/") ? dir.substring(0, dir.length() - 1) : dir;
            String[] arr = dir.split("/");
            StringBuffer sbfDir = new StringBuffer();
            //循环生成子目录
            for (String s : arr) {
                //0625 会到根目录下创建文件夹 没有权限导致创建失败
//                sbfDir.append("/");
                sbfDir.append(s);
                //目录编码，解决中文路径问题
                d = new String(sbfDir.toString().getBytes("GBK"), "iso-8859-1");
                //尝试切入目录
                if(ftpClient.changeWorkingDirectory(d)){
                    continue;
                }
                if(!ftpClient.makeDirectory(d)) {
                    log.error("[失败]ftp创建目录：" + sbfDir.toString());
                    return false;
                }
                log.info("[成功]创建ftp目录：" + sbfDir.toString());
            }
            //将目录切换至指定路径
            return ftpClient.changeWorkingDirectory(d);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
