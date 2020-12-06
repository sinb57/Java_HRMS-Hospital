package main.page;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
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

public class ReservationHistoryPage extends JPanel {

	PageHandler pageHandler;
	ArrayList<Reservation> reservationList;
	ArrayList<String[]> list = new ArrayList<>();
	String col[] = { "번호", "환자 이름", "연락처", "진료 의사", "진료 과목", "진료 일자", "예약 시간", "상세 보기" };
	private JScrollPane pane;
	private JPanel reservationHistoryPanel;
	private String startDate = "2020-11-23";
	private String endDate = "2020-11-30";
	private String searchType = "이름";
	private String searchField = "";

	private String[][] getPatients(String state, String start, String end, String type, String value) {
		reservationList = pageHandler.service.getReservationList(state, start, end, type, value);

		String patientId = "";
		String patientName = "";
		String patientPhoneNumber = "";

		try {
			String[][] tmplist = new String[reservationList.size()][];

			for (int i = 0; i < reservationList.size(); i++) {
				patientId = reservationList.get(i).getPatientId();
				Patient patient = pageHandler.service.getPateintInfo(patientId);
				patientName = patient.getPatientname();
				patientPhoneNumber = patient.getPhoneNumber();

				Reservation reservation = reservationList.get(i);

				tmplist[i] = (new String[] { Integer.toString(i + 1), patientName, patientPhoneNumber,
						reservation.getDocterName(), reservation.getCareType(), reservation.getReservationDate(),
						reservation.getReservationTime(), patient.getPatientId() + " "
								+ reservation.getReservationDate() + " " + reservation.getReservationTime() });
			}
			return tmplist;

		} catch (Exception e) {
			System.out.println("데이터 없음");
		}

		return null;
	}

	public ReservationHistoryPage(PageHandler pageHandler) {
		this.pageHandler = pageHandler;
		this.setLayout(null);

		reservationHistoryPanel = new JPanel();
		reservationHistoryPanel.setVisible(true);
		reservationHistoryPanel.setLayout(null);
		reservationHistoryPanel.setBackground(Color.white);
		reservationHistoryPanel.setBounds(0, -70, 1240, 845);

		JLabel reservationHistoryStartDayLabel = new JLabel("시작일: ");
		reservationHistoryStartDayLabel.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		reservationHistoryStartDayLabel.setBounds(80, 70, 150, 120);
		reservationHistoryPanel.add(reservationHistoryStartDayLabel);

		JTextField reservationHistoryStartDayField = new JTextField(startDate);
		reservationHistoryStartDayField.setBounds(170, 113, 150, 35);
		reservationHistoryPanel.add(reservationHistoryStartDayField);

		JLabel reservationHistoryEndDayLabel = new JLabel("~    종료일: ");
		reservationHistoryEndDayLabel.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		reservationHistoryEndDayLabel.setBounds(350, 70, 150, 120);
		reservationHistoryPanel.add(reservationHistoryEndDayLabel);

		JTextField reservationHistoryEndDayField = new JTextField(endDate);
		reservationHistoryEndDayField.setBounds(480, 113, 150, 35);
		reservationHistoryPanel.add(reservationHistoryEndDayField);

		String[] select = { "이름", "연락처", "진료 의사", "진료 시간" };
		JComboBox reservationHistoryBox = new JComboBox(select);
		reservationHistoryBox.setBounds(700, 113, 100, 35);
		reservationHistoryBox.setSelectedItem(searchType);
		reservationHistoryPanel.add(reservationHistoryBox);

		JTextField reservationHistorySearchTextField = new JTextField(20);
		reservationHistorySearchTextField.setBounds(830, 113, 200, 35);
		reservationHistorySearchTextField.setText(searchField);
		reservationHistoryPanel.add(reservationHistorySearchTextField);

		JButton reservationHistorySearchButton = new JButton("검색");
		reservationHistorySearchButton.setBounds(1050, 113, 80, 35);
		reservationHistorySearchButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				startDate = reservationHistoryStartDayField.getText();
				endDate = reservationHistoryEndDayField.getText();
				searchType = (String) reservationHistoryBox.getSelectedItem();
				searchField = reservationHistorySearchTextField.getText();

				String[][] patients = getPatients("전체", startDate, endDate, searchType, searchField);

				reload(patients);
			}
		});
		reservationHistoryPanel.add(reservationHistorySearchButton);

		String[][] patients = getPatients("전체", startDate, endDate, "", "");

		pane = createTable(patients);

		reservationHistoryPanel.add(pane);
		reservationHistoryPanel.setBackground(Color.white);

		this.add(reservationHistoryPanel);

	}

	public JScrollPane createTable(String[][] patients) {
		DefaultTableModel defaultTable = new DefaultTableModel(patients, col);
		JTable table = new JTable(defaultTable);
		table.setRowHeight(40);
		table.setBounds(100, 100, 100, 100);

		DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();
		dtcr.setHorizontalAlignment(SwingConstants.CENTER);

		TableColumnModel tcm = table.getColumnModel();

		for (int i = 0; i < tcm.getColumnCount(); i++) {
			tcm.getColumn(i).setCellRenderer(dtcr);
		}

		JScrollPane pane = new JScrollPane(table);
		pane.setBounds(60, 180, 1100, 510);

		table.getColumnModel().getColumn(7).setCellRenderer(new TableCell());
		table.getColumnModel().getColumn(7).setCellEditor(new TableCell());

		return pane;
	}

	private void reload(String[][] patients) {
		reservationHistoryPanel.remove(pane);
		pane = createTable(patients);
		reservationHistoryPanel.add(pane);
		revalidate();
		repaint();
	}

	class TableCell extends AbstractCellEditor implements TableCellEditor, TableCellRenderer {
		JButton button;
		String patientId;
		String reservationDate;
		String reservationTime;

		public TableCell() {
			button = new JButton("자세히");
			button.addActionListener(e -> {
				pageHandler.patientId = this.patientId;
				pageHandler.reservationDate = this.reservationDate;
				pageHandler.reservationTime = this.reservationTime;
				pageHandler.change("PatientInformationPage");
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
			String[] data = ((String) value).split(" ");
			patientId = data[0];
			reservationDate = data[1];
			reservationTime = data[2];
			return button;
		}
	}
}