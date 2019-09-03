package view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

import dao.DVDDao;
import dao.impl.DVDDaoImpl;
import dvdUtil.DVDUtil;
import emptity.DVD;

public class DVDRent2 extends JInternalFrame {

	private static final long serialVersionUID = 1L;
	
	private JPanel jp_main=null;
	private JPanel jp_center=null;
	private JPanel jp_right=null;
	private JPanel jp_under=null;
	
	private JTable jt_text=null;
	private JLabel jl_type=null;
	private JComboBox<String> jc_type=null;
	private JTextField jt_content=null;
	private JButton jb_search=null;
	private JButton jb_add=null;
	private JButton jb_update=null;
	private JButton jb_delete=null;
	private JButton jb_exit=null;
	private JLabel jl_dname=null;
	private JLabel jl_dcount=null;
	private JLabel jl_status=null;
	private JTextField jt_dname=null;
	private JTextField jt_dcount=null;
	private JComboBox<String> jc_status=null;
	private DVDDao dvdDao=null;
	private List<DVD> list=null;
	private ToTable toTable=null;
	private JScrollPane js_table=null;
	
	public DVDRent2() {
		dvdDao=new DVDDaoImpl();
		init();
		Register();
	}
	
	private void init() {
		jp_main=new JPanel();
		jp_main.setLayout(new BorderLayout());
		jp_center=new JPanel();
		jp_center.setLayout(new BorderLayout());
		jp_center.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(null,null),"��ѯ��Ϣ"));
		jp_right=new JPanel();
		jp_right.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(null,null),"��ѯ����"));
		jp_right.setLayout(new GridLayout(9, 1,0,5));
		jp_under=new JPanel();
		jp_under.setLayout(new GridLayout(1,6));
		list=new ArrayList<>();
		
		jt_text=new JTable();
		js_table=new JScrollPane(jt_text);
		jl_dname=new JLabel("DVD����");
		jt_dname=new JTextField();
		jl_dcount=new JLabel("�������");
		jt_dcount=new JTextField();
		jl_status=new JLabel("DVD״̬");
		jc_status=new JComboBox<String>(new String[] {"�ɽ�","�ѽ�"});
		jl_type=new JLabel("��ѯ����");
		jc_type=new JComboBox<String>(new String[] {"ȫ��DVD","DVD����","DVD���"});
		jt_content=new JTextField();
		jt_content.setEditable(false);
		jb_search=new JButton("��   ѯ");
		jb_add=new JButton("��   ��");
		jb_update=new JButton("��   ��");
		jb_update.setEnabled(false);
		jb_delete=new JButton("ɾ��");
		jb_delete.setEnabled(false);
		jb_exit=new JButton("��   ��");
		
		jp_under.add(jl_dname);
		jp_under.add(jt_dname);
		jp_under.add(jl_dcount);
		jp_under.add(jt_dcount);
		jp_under.add(jl_status);
		jp_under.add(jc_status);
		jp_center.add(jp_under,BorderLayout.SOUTH);
		jp_center.add(js_table);
		jp_right.add(jl_type);
		jp_right.add(jc_type);
		jp_right.add(jt_content);
		jp_right.add(jb_search);
		jp_right.add(jb_add);
		jp_right.add(jb_update);
		jp_right.add(jb_delete);
		jp_right.add(new JLabel());
		jp_right.add(jb_exit);
		
		jp_main.add(jp_right,BorderLayout.EAST);
		jp_main.add(jp_center,BorderLayout.CENTER);
		this.add(jp_main);
		
		this.setTitle("DVD��ѯ����");
		this.setSize(640, 400);
		this.setClosable(true);
		this.setIconifiable(true);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setVisible(true);
	}
	
	private void Register() {
		
		jb_exit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int flag=JOptionPane.showInternalConfirmDialog(DVDRent2.this, "�Ƿ��˳�",null,JOptionPane.YES_NO_OPTION);
				if(flag==JOptionPane.YES_OPTION) {
					DVDRent2.this.dispose();
				}
			}
		});
		
		jb_delete.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int row=jt_text.getSelectedRow();
				int id=(Integer)jt_text.getValueAt(row, 0);
				int flag=JOptionPane.showInternalConfirmDialog(DVDRent2.this, "�Ƿ�ɾ������DVD��Ϣ",null,JOptionPane.YES_NO_OPTION);
				if(flag==JOptionPane.YES_OPTION) {
					boolean flag1=dvdDao.delDVD(id);
					if(flag1) {
						JOptionPane.showInternalMessageDialog(DVDRent2.this, "ɾ���ɹ�");
					}else {
						JOptionPane.showInternalMessageDialog(DVDRent2.this, "ɾ��ʧ��");
					}
				}
				
			}
		});
		jb_update.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String dname=jt_dname.getText().trim();
				String dcount=jt_dcount.getText().trim();
				int status=jc_status.getSelectedIndex();
				if(dname.equals("")) {
					JOptionPane.showMessageDialog(DVDRent2.this,"DVD���ֲ���Ϊ��");
					return;
				}
				if(dcount.equals("")) {
					JOptionPane.showMessageDialog(DVDRent2.this,"DVD�����������Ϊ��");
					return;
				}
				if(!DVDUtil.isNumber(dcount)) {
					JOptionPane.showMessageDialog(DVDRent2.this,"DVD�������ֻ��������");
					return;
				}
				int flag=JOptionPane.showInternalConfirmDialog(DVDRent2.this, "�Ƿ����DVD�����Ϣ",null,JOptionPane.YES_NO_OPTION);
				if(flag==JOptionPane.YES_OPTION) {
					int row=jt_text.getSelectedRow();
					DVD dvd=new DVD((Integer)jt_text.getValueAt(row, 0),dname,Integer.parseInt(dcount),status);
					boolean flag1=dvdDao.updateDVD(dvd);
					if(flag1) {
						JOptionPane.showInternalMessageDialog(DVDRent2.this, "���³ɹ�");
					}else {
						JOptionPane.showInternalMessageDialog(DVDRent2.this, "����ʧ��");
					}
				}
			}
		});
		jt_text.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(jt_text.getSelectedRow()!=-1) {
					jb_update.setEnabled(true);
					jb_delete.setEnabled(true);
				}
				int  row=jt_text.getSelectedRow();
				String dname=jt_text.getValueAt(row, 1).toString();
				String dcount=jt_text.getValueAt(row, 2).toString();
				String status=jt_text.getValueAt(row, 3).toString();
				jt_dname.setText(dname);
				jt_dcount.setText(dcount);
				jc_status.setSelectedItem(status);
			}
		});
		jc_type.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				String item=e.getItem().toString();
				jt_content.setText("");
				if(item.equals("ȫ��DVD")) {
					jt_content.setEditable(false);
				}else {
					jt_content.setEditable(true);
				}
			}
		});
		jb_search.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int index=jc_type.getSelectedIndex();
				String content=jt_content.getText().trim();
				if(index!=0&&content.equals("")) {
					JOptionPane.showMessageDialog(DVDRent2.this,"��ѯ���ݲ���Ϊ��");
					return;
				}
				if(!list.isEmpty()) {
					list.clear();
				}
				if(index==0) {
					list=dvdDao.selectDVD();
				}else if(index==1) {
					DVD dvd=dvdDao.selectDVDName(content);
					list.add(dvd);
				}else {
					if(DVDUtil.isNumber(content)) {
						DVD dvd=dvdDao.selectDVDId(Integer.parseInt(content));
						if(dvd!=null) {
							list.add(dvd);
						}else {
							JOptionPane.showMessageDialog(DVDRent2.this,"û�д�DVD");
						}
					}else {
						JOptionPane.showMessageDialog(DVDRent2.this,"���������ֻ��Ϊ����");
					}
				}
				reflush(list);
			}
		});
		jb_add.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String dname=jt_dname.getText().trim();
				String dcount=jt_dcount.getText().trim();
				int status=jc_status.getSelectedIndex();
				if(dname.equals("")) {
					JOptionPane.showMessageDialog(DVDRent2.this,"DVD���ֲ���Ϊ��");
					return;
				}
				if(dcount.equals("")) {
					JOptionPane.showMessageDialog(DVDRent2.this,"DVD�����������Ϊ��");
					return;
				}
				if(!DVDUtil.isNumber(dcount)) {
					JOptionPane.showMessageDialog(DVDRent2.this,"DVD�������ֻ��������");
					return;
				}
				int flag=JOptionPane.showInternalConfirmDialog(DVDRent2.this, "�Ƿ����DVD",null,JOptionPane.YES_NO_OPTION);
				if(flag==JOptionPane.YES_OPTION) {
					DVD dvd=new DVD(dname,Integer.parseInt(dcount),status);
					boolean b1=dvdDao.saveDVD(dvd);
					if(b1) {
						JOptionPane.showInternalMessageDialog(DVDRent2.this, "��ӳɹ�");
					}else {
						JOptionPane.showInternalMessageDialog(DVDRent2.this, "���ʧ��");
					}
				}
			}
		});
	}
	
	private class ToTable implements TableModel{
		private List<DVD> list=null;
		
		public ToTable(List<DVD> list) {
			this.list=list;
		}
		@Override
		public int getRowCount() {
			return list.size();
		}

		@Override
		public int getColumnCount() {
			return 4;
		}

		@Override
		public String getColumnName(int columnIndex) {
			if(columnIndex==0) {
				return "Ӱ��ID";
			}else if(columnIndex==1) {
				return "DVD����";
			}else if(columnIndex==2) {
				return "DVD�������";
			}else if(columnIndex==3) {
				return "DVD״̬";
			}else {
				return "����";
			}
		}

		@Override
		public Class<?> getColumnClass(int columnIndex) {
			return String.class;
		}

		@Override
		public boolean isCellEditable(int rowIndex, int columnIndex) {
  			return false;
		}

		@Override
		public Object getValueAt(int rowIndex, int columnIndex) {
			DVD dvd=list.get(rowIndex);
			if(columnIndex==0) {
				return dvd.getId();
			}else if(columnIndex==1) {
				return dvd.getDname();
			}else if(columnIndex==2) {
				return dvd.getDcount();
			}else if(columnIndex==3) {
				return (dvd.getStatus()==1?"�ѽ�":"�ɽ�");
			}else {
				return "����";
			}
		}

		@Override
		public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
			
		}

		@Override
		public void addTableModelListener(TableModelListener l) {
			
		}

		@Override
		public void removeTableModelListener(TableModelListener l) {
			
		}
		
	}
	
	private void reflush(List<DVD> list) {
		
		toTable=new ToTable(list);
		jt_text.setModel(toTable);
	}
}
