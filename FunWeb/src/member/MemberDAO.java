package member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

//DAO
//DB�� ������ ��� �۾� �ϴ� Ŭ���� 
public class MemberDAO {

	//jspbeginner�����ͺ��̽��� ������ �δ� �޼ҵ�
	private Connection getConnection() throws Exception {
		
		Connection con = null;
		Context init = new InitialContext();
		//Ŀ�ؼ�Ǯ ���
		DataSource ds = 
				(DataSource)init.lookup("java:comp/env/jdbc/jspbeginner");
		//Ŀ�ؼ�Ǯ�� ���� Ŀ�ؼǰ�ü(DB�� �̸� ���� �Ǿ� �ִ� ������ �˸��� ��ü) ��������
		con = ds.getConnection();
		
		return con;
	}
	
	//�α��� ó����.. ����ϴ� �޼ҵ�
	//�Է¹��� id,pass���� DB�� ����Ǿ� �ִ� id,pass���� Ȯ�� �Ͽ� �α��� ó�� 
	public int userCheck(String id,String passwd){
		int check = -1; // 1 -> ���̵� ����, ��й�ȣ ����
						// 0 -> ���̵� ����, ��й�ȣ Ʋ��
						// -1 -> ���̵� Ʋ��
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "";
		
		try {
			//1. DB���� ��ü ��� (Ŀ�ؼ�Ǯ�� ���� Ŀ�ؼ� ��ü ���)
			con = getConnection();
			
			//2. SQL(SELECT)����� -> �Ű������� ���� �޴� id�� �ش��ϴ� ���ڵ� �˻�
			sql = "select * from member where id=?";
			
			//3. SQL������ ��ü PreparedStatement ���
			pstmt = con.prepareStatement(sql);
			
			//4. ?�� ���� �Ǵ� �� ����
			pstmt.setString(1, id);
			
			//5.SELECT���� ������ �װ���� ResultSet�� ������ ���
			rs = pstmt.executeQuery();
			
			if(rs.next()){//�α��� �ϱ� ���� �Է��� id�� ���� �ϰ�....
				
				//�α��ν�.. �Է��� ��й�ȣ�� DB�� ����Ǿ� �ִ� �˻��� ��й�ȣ�� ������..
				if(passwd.equals(rs.getString("passwd"))){
					
					check = 1; //���̵� ����, ��й�ȣ ����  �Ǻ��� 1����
					
				}else{//id�� ������.. ��й�ȣ�� �ٸ��ٸ�..
					
					check = 0;
				}
				
			}else{//id�� DB�� ���� ���� �ʴ´�.
				check = -1;	
			}
	
		} catch (Exception e) {
			System.out.println("userCheck�޼ҵ� ���ο��� ���� : " + e);
		} finally {
			try {
				//�ڿ�����
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(con != null) con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return check; // 1 �Ǵ� 0 �Ǵ� -1 �� ���� //loginPro.jsp�� ����
	
	}//userCheck �޼ҵ� �ݴ� ��ȣ
	
	
	//ȸ�������� ����.. ����ڰ� �Է��� id���� �Ű������� ���� �޾�..
	//DB�� ����ڰ� �Է��� id���� ���� �ϴ��� SELECT�˻� �Ͽ�..
	//���� ����ڰ� �Է��� id�� �ش� �ϴ� ȸ�� ���ڵ尡 �˻� �Ǹ�?
	//check�������� 1�� ���� �Ͽ�<------���̵� �ߺ� ���� ��Ÿ����,
	//���� ����ڰ� �Է��� id�� �ش� �ϴ� ȸ�� ���ڵ尡 �˻��� ���� ������?
	//check�������� 0���� ���� �Ͽ�<-----���̵� �ߺ� �ƴ��� ��Ÿ����....
	//���������... ���̵� �ߺ��̳�,...�ߺ��� �ƴϳ� �� check������ ����Ǿ� �����Ƿ�..
	//check�������� ���� �Ѵ�.
	public int idCheck(String id){
		
		int check = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql="";
		try {
			//1. DB���� (Ŀ�ؼ�Ǯ �κ��� Ŀ�ؼ� ���)
			con = getConnection();
			
			//2. SQL�� �����(SELECT)-> �Ű������� ���޹��� �Է��� ���̵� �ش��ϴ� ���ڵ� �˻�
			sql = "select * from member where id=?";
			
			//3.SQL������ ������ PreparedStatement��ü ���
			//?��ȣ�� �����Ǵ� SELECT���� ������ ������ ��ü SELECT������
			//PreparedStatement��ü�� ��� ��ȯ �ޱ�
			pstmt = con.prepareStatement(sql);
			
			//4. ?��ȣ�� ���� �Ǵ� �� ����
			pstmt.setString(1, id);
			
			//5.PreparedStatement��ü�� executeQuery()�޼ҵ带 ȣ���Ͽ�...
			//�˻�!!!! �˻��� �װ���� ResultSet�� ��� ��ȯ
			rs = pstmt.executeQuery();
			
			//6. �츮�� �Է��� ���̵� �ش��ϴ� ���ڵ尡 �˻� �Ǿ��ٸ�?(id�� ���� �ϸ�,id���ߺ��Ǿ��ٸ�)
			if(rs.next()){
				check = 1;
			}else{//�Է��� ���̵� �ش��ϴ� ȸ�����ڵ尡 �˻� ���� ������?
				  //(id�� �ߺ� ���� �ʾҴٸ�)
				check = 0;
			}
		} catch (Exception e) {
			System.out.println("idCheck()�޼ҵ忡�� ���� : " + e);
		} finally {
			
			try {
				//�ڿ�����
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(con != null) con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}//finally
		
		//7. check������ ����
		return check; //1�Ǵ� 0�� ����
		
	}//idCheck()�޼ҵ� �ݴ� ��ȣ 
	
	
	//joinPro.jsp���� �Ű������� ���� ���� MemberBean�� DB�� �߰� ��Ű�� �޼ҵ�
	public void insertMember(MemberBean memberBean){
		
		Connection con = null;
		PreparedStatement pstmt = null;
		//insert������ ����� ������ ����
		String sql = "";
		
		try {
			//1. DB���� (Ŀ�ؼ�Ǯ �κ��� Ŀ�ؼ� ���)
			con = getConnection();
			
			//2.SQL���� ����� (INSERT)
			sql = "insert into member(id,passwd,name,reg_date,email,address,tel,mtel)"
				+ "values(?,?,?,?,?,?,?,?)";
			
			//3.SQL������ ������ PreparedStatement��ü ���
			//?��ȣ�� �����Ǵ� insert�Ұ��� ������ ������ ��ü insert������ PreparedStatement��ü�� ���
			//��ȯ �ޱ�
			pstmt = con.prepareStatement(sql);
			
			//4. ?��ȣ�� �����Ǵ� insert�� ����  ����
			pstmt.setString(1, memberBean.getId());
			pstmt.setString(2, memberBean.getPasswd());
			pstmt.setString(3, memberBean.getName());
			pstmt.setTimestamp(4, memberBean.getReg_date());
			pstmt.setString(5, memberBean.getEmail());
			pstmt.setString(6, memberBean.getAddress());
			pstmt.setString(7, memberBean.getTel());
			pstmt.setString(8, memberBean.getMtel());
			
			//5. PreparedStatement�� ������ insert��ü ������ DB�� ���� �Ͽ� ����
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			System.out.println("insertMember�޼ҵ� ���ο��� ���� : " + e);
		} finally {
			//6. �ڿ�����
			
			try {
				if(pstmt != null){//��� �ϰ� ������
					pstmt.close();
				}
				if(con != null){//��� �ϰ� ������
					con.close();
				}
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		}//finally �ݴ� �κ�	
	}//insertMember�޼ҵ� �ݴ� �κ�	
	
}//MemberDAOŬ���� �ݴ� �κ�




