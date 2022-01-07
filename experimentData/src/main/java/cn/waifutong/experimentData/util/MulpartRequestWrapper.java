package cn.waifutong.experimentData.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.util.Streams;
import org.apache.commons.io.FilenameUtils;

/**
 * 获取文件完整路径.名称
 * 
 */
public class MulpartRequestWrapper extends HttpServletRequestWrapper {
	private Map<String, String[]> map;
	private String path = "";
	private String realPath = "";
	private String prefix;
	private String suffix;
	public String allRealPath;
	private String str;
	private String strPath;
	private String filePath;
	private List<String> allPathList = new ArrayList<String>();
	private List<String> allPrefixList = new ArrayList<String>();
	private List<String> allSuffixList = new ArrayList<String>();

	public MulpartRequestWrapper(HttpServletRequest request, String type) {
		super(request);
		try {
			boolean isMultpart = ServletFileUpload.isMultipartContent(request);
			if (isMultpart) {
			    
				ServletFileUpload upload = new ServletFileUpload();
				FileItemIterator iters = upload.getItemIterator(request);
				FileItemStream item = null;
				
				InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("globalSetting.properties");
				Properties p = new Properties();
				p.load(inputStream);
				SimpleDateFormat sdfDay = new SimpleDateFormat("yyyyMMdd");

				while (iters.hasNext()) {
				    
	                path = "/" + sdfDay.format(new Date()) + "/";

	                Properties prop = System.getProperties();
	                String os = prop.getProperty("os.name");
	                if ("win".equals(os.substring(0, 3).toLowerCase())) {
	                    realPath = p.getProperty("default.winBasePath") + "/" + path;
	                } else {
	                    realPath = p.getProperty("default.linuxBasePath") + "/" + path;
	                }
	                if ("product".equals(type)) {
	                    if ("win".equals(os.substring(0, 3).toLowerCase())) {
	                        realPath = p.getProperty("product.winBasePath") + "/" + path;
	                    } else {
	                        realPath = p.getProperty("product.linuxBasePath") + "/" + path;
	                    }
	                }
	                File folder = new File(realPath.replace("/", File.separator));
	                if (!folder.exists() || !folder.isDirectory()) {
	                    folder.mkdirs();
	                }
	                
					item = iters.next();
					String name = item.getFieldName();
					InputStream in = item.openStream();
					if (!item.isFormField()) {
						String fileName = item.getName();
						if (fileName != null && !"".equals(fileName)) {
							fileName = FilenameUtils.getName(fileName);
                            /*prefix = fileName.split("\\.")[0];
                            suffix = fileName.split("\\.")[1];*/
							int lastIndexOfDot = fileName.lastIndexOf(".");
							prefix = fileName.substring(0, lastIndexOfDot);
			                suffix = fileName.substring(lastIndexOfDot + 1);
			                UUID uuid = UUID.randomUUID();
			                String str = uuid.toString();
			                // 去掉"-"符号
			                String temp = str.substring(0, 8) + str.substring(9, 13) + str.substring(14, 18) + str.substring(19, 23)
			                        + str.substring(24);

			                fileName = temp + "." + suffix;
			                String realFileName = fileName;
							path += realFileName;
							path = path.replace("\\", "/");
							//this.setFilePath(path);
							realPath += File.separator + fileName;
							realPath = realPath.replace("\\", "/");
							Streams.copy(in, new FileOutputStream(realPath),
									true);
							if (type == null || "".equals(type) || "null".equals(type)) {
			                    path = "/file" + path;
			                } else {
			                    path = "/" + type + path;
			                }
							allPathList.add(path);
							allPrefixList.add(prefix);
							allSuffixList.add(suffix);
							realPath = str;
							path = strPath;
						} else {
							allPathList.add(fileName);
						}
					} else {
						String value = Streams.asString(in, "UTF-8");
						this.paramsLoadToMap(name, value);
					}
				}
			} else {
				map = request.getParameterMap();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	private void paramsLoadToMap(String name, String value) {
		String[] fieldValues = (String[]) map.get(name);
		if (fieldValues == null) {
			map.put(name, new String[] { value });
		} else {
			fieldValues = Arrays.copyOf(fieldValues, fieldValues.length + 1);
			fieldValues[fieldValues.length - 1] = value;
			map.put(name, fieldValues);
		}
	}

	public String getAllRealPath() {
		return allRealPath;
	}

	public void setAllRealPath(String allRealPath) {
		this.allRealPath = allRealPath;
	}

	@Override
	public String getParameter(String name) {
		String[] values = (String[]) map.get(name);
		if (values != null) {
			return values[0];
		}
		return null;
	}

	@Override
	public String[] getParameterValues(String name) {
		String[] values = (String[]) map.get(name);
		return values;
	}

	@Override
	public Map<String, String[]> getParameterMap() {
		return map;
	}

	@Override
	public String toString() {
		return this.path;
	}

	public String getRealFilePath(String path, HttpServletRequest request) {
		String realPath = null;
		realPath = request.getSession().getServletContext().getRealPath(path);
		return realPath;
	}

	public List<String> getAllPathList() {
		return allPathList;
	}

	public void setAllPathList(List<String> allPathList) {
		this.allPathList = allPathList;
	}

	public String getRealPath() {
		return realPath;
	}

	public void setRealPath(String realPath) {
		this.realPath = realPath;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public List<String> getAllPrefixList() {
		return allPrefixList;
	}

	public void setAllPrefixList(List<String> allPrefixList) {
		this.allPrefixList = allPrefixList;
	}

	public List<String> getAllSuffixList() {
		return allSuffixList;
	}

	public void setAllSuffixList(List<String> allSuffixList) {
		this.allSuffixList = allSuffixList;
	}

}
