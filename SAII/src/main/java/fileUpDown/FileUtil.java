//  ↓ request 객체,               ↓ 저장될 서버 경로,       ↓ 파일 최대 크기,    ↓ 인코딩 방식,       ↓ 같은 이름의 파일명 방지 처리
// (HttpServletRequest request, String saveDirectory, int maxPostSize, String encoding, FileRenamePolicy policy)
package fileUpDown;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;

public class FileUtil {

	public static MultipartRequest uploadFile(HttpServletRequest req, String saveDirectory, int maxPostSize) {
		try {
			return new MultipartRequest(req, saveDirectory,maxPostSize,"UTF-8");
			
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	//Download.jsp에서는 내장객체를 사용하여 다운로드했다면,
	//FileUtil.java에서는 출력스트림 내장객체가 아닌 파라미터를 사용하여 다운로드 메소드 만듦
	//(내장객체를 호출하여 사용할 수 없기 때문에 response와 request 매개변수를 따로 받아 사용함).
	//특정 페이지에 국한된 기능을 떼어내서 메소드로 만듦
	public static void download(HttpServletRequest req, HttpServletResponse resp,
			String directory, String sFilename, String oFilename) {
		String saveDirectory = req.getServletContext().getRealPath(directory);
		try{
			//파일을 찾아 입력 스트림 생성
			File file = new File(saveDirectory, sFilename);
			InputStream inStream = new FileInputStream(file);
			
			//한글 파일명 깨짐 방지
			String client = req.getHeader("User-Agent");
			if(client.indexOf("WOW64") == -1){
				oFilename = new String(oFilename.getBytes("UTF-8"), "ISO-8859-1");
			}
			else{
				oFilename = new String(oFilename.getBytes("KSC5601"), "ISO-8859-1");
			}
			
			//파일 다운로드용 응답 헤더 설정
			resp.reset();
			resp.setContentType("application/octet-stream");
			resp.setHeader("Content-Disposition", "attachment; filename=\"" + oFilename + "\"");
			resp.setHeader("Content-Length", "" + file.length());
			
			//response 내장 객체로부터 새로운 출력 스트림 생성
			OutputStream outStream = resp.getOutputStream();
			
			//출력 스트림에 파일 내용 출력
			byte b[] = new byte[(int)file.length()];
			int readBuffer = 0;
			while ((readBuffer = inStream.read(b)) > 0){
				outStream.write(b,0,readBuffer);
			}
			//입/출력 스트림 닫음
			inStream.close();
			outStream.close();
		}
		catch (FileNotFoundException e){
			System.out.println("파일을 찾을 수 없습니다.");
		}
		catch (Exception e){
			System.out.println("예외가 발생하였습니다.");
		}
		
	
	}
	
	//파일 삭제
	public static void deleteFile(HttpServletRequest req,
			String directory, String filename) {	//어떤 걸 지울지 지정하는 인자값 - 저장되어있는 장소 directory와 저장되어있는 filename
		
		//실제 지울 디렉토리 경로를 얻어옴
		String saveDirectory = req.getServletContext().getRealPath("/Storage");
	
		//지우려고 하는 파일의 경로를 받아서 파일 인스턴스 생성
		File file=new File(saveDirectory+File.separator+filename);
		
		//경로 안에 실제로 파일이 존재하는지 확인하고 파일을 지움.
		if(file.exists()) {
			file.delete();		
		}
	
	}	

		
}
