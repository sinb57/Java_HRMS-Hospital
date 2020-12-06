package main.page;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

import main.domain.Patient;
import main.domain.Reservation;

public class ReservationStatusPage extends JPanel {
	private PageHandler pageHandler;
	private ArrayList<Reservation> reservationList;
	private JTable reservationStatusTable;
	private JScrollPane pane;
	private JPanel researvationStatusPanel;

	private String col[] = { "번호", "이름", "연락처", "예약 시간", "처리", "취소" };

	private String[][] getPatients(String today) {

		String patientId = "";
		String patientName = "";
		String patientPhoneNumber = "";

		try {
			reservationList = pageHandler.service.getReservationList("예약중", "2020-12-01", "2020-12-01", "", "");
			String[][] tmplist = new String[reservationList.size()][];
			for (int i = 0; i < reservationList.size(); i++) {
				patientId = reservationList.get(i).getPatientId();
				Patient patient = pageHandler.service.getPateintInfo(patientId);

				patientName = patient.getPatientname();
				patientPhoneNumber = patient.getPhoneNumber();

				tmplist[i] = (new String[] { Integer.toString(i + 1), patientName, patientPhoneNumber,
						reservationList.get(i).getReservationTime(), "", "" });
			}
			return tmplist;

		} catch (Exception e) {
			System.out.println("데이터 없음");
			JOptionPane.showMessageDialog(null, "데이터가 존재하지 않습니다.");
		}

		return null;
	}

	public ReservationStatusPage(PageHandler pageHandler) {
		this.setLayout(null);
		this.pageHandler = pageHandler;

		researvationStatusPanel = new JPanel();
		researvationStatusPanel.setVisible(true);
		researvationStatusPanel.setLayout(null);
		researvationStatusPanel.setBackground(Color.white);
		researvationStatusPanel.setBounds(0, -90, 1240, 845);

		pane = reRoadTable();

		researvationStatusPanel.add(pane);
		researvationStatusPanel.setBackground(Color.white);

		this.add(researvationStatusPanel);

	}

	public JScrollPane reRoadTable() {
		DefaultTableModel defaultTable = new DefaultTableModel(getPatients("2020-11-28"), col);
		JTable reservationStatusTable = new JTable(defaultTable);
		reservationStatusTable.setRowHeight(40);

		DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();
		dtcr.setHorizontalAlignment(SwingConstants.CENTER);

		TableColumnModel tcm = reservationStatusTable.getColumnModel();

		for (int i = 0; i < tcm.getColumnCount(); i++) {
			tcm.getColumn(i).setCellRenderer(dtcr);
		}

		reservationStatusTable.getColumnModel().getColumn(4).setCellRenderer(new CompleteTableCell());
		reservationStatusTable.getColumnModel().getColumn(4).setCellEditor(new CompleteTableCell());

		reservationStatusTable.getColumnModel().getColumn(5).setCellRenderer(new CancelTableCell());
		reservationStatusTable.getColumnModel().getColumn(5).setCellEditor(new CancelTableCell());

		this.reservationStatusTable = reservationStatusTable;

		pane = new JScrollPane(reservationStatusTable);
		pane.setBounds(60, 180, 1100, 510);

		return pane;
	}

	private void reload() {

		researvationStatusPanel.remove(pane);
		pane = reRoadTable();
		researvationStatusPanel.add(pane);
		revalidate();
		repaint();
	}

	class CompleteTableCell extends AbstractCellEditor implements TableCellEditor, TableCellRenderer {
		JButton button;
		String patientId;
		String reservationDate;
		String reservationTime;

		public CompleteTableCell() {
			button = new JButton("처리");
			button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					int index = Integer.parseInt(
							(String) reservationStatusTable.getValueAt(reservationStatusTable.getSelectedRow(), 0));

					String patientId = reservationList.get(index - 1).getPatientId();
					String reservationDate = reservationList.get(index - 1).getReservationDate();
					String reservationTime = reservationList.get(index - 1).getReservationTime();
					TypeDoctorNamePage typeDoctorNamePage = new TypeDoctorNamePage(pageHandler, patientId,
							reservationDate, reservationTime);
					typeDoctorNamePage.load();
					reload();

				}
			});
		}

		@Override
		public Object getCellEditorValue() {
			return null;
		}

		@Override
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
				int row, int column) {
			return button;
		}

		@Override
		public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row,
				int column) {
			return button;
		}
	}

	class CancelTableCell extends AbstractCellEditor implements TableCellEditor, TableCellRenderer {
		JButton button;

		public CancelTableCell() {
			button = new JButton("취소");
			button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					int index = Integer.parseInt(
							(String) reservationStatusTable.getValueAt(reservationStatusTable.getSelectedRow(), 0));

					String patientId = reservationList.get(index - 1).getPatientId();
					String reservationDate = reservationList.get(index - 1).getReservationDate();
					String reservationTime = reservationList.get(index - 1).getReservationTime();
					if (pageHandler.service.cancelReservation(patientId, reservationDate, reservationTime))
						reload();
				}
			});
		}

		@Override
		public Object getCellEditorValue() {
			return null;
		}

		@Override
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
				int row, int column) {
			return button;
		}

		@Override
		public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row,
				int column) {
			return button;
		}
	}

}
