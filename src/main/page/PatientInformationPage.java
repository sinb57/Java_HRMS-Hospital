package main.page;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

import main.domain.*;

public class PatientInformationPage extends JPanel {

	PageHandler pageHandler;
	String patientId;
	String reservationDate;
	String reservationTime;

	public PatientInformationPage(PageHandler pageHandler, String patientId, String reservationDate,
			String reservationTime) {
		this.pageHandler = pageHandler;
		this.patientId = patientId;
		this.reservationDate = reservationDate;
		this.reservationTime = reservationTime;

		this.setLayout(null);

		load();
	}

	private void load() {

		Patient patient = pageHandler.service.getPateintInfo(patientId);
		patient.setReservationList(pageHandler.service.getReservationListOfPatient(patientId));
		Reservation thisReservation = patient.searchReservaion(reservationDate, reservationTime);

		JPanel patientInformationPanel = new JPanel();
		patientInformationPanel.setVisible(true);
		patientInformationPanel.setLayout(null);
		patientInformationPanel.setBackground(Color.WHITE);
		patientInformationPanel.setBounds(0, -70, 1240, 845);

		JLabel patientNameLabel = new JLabel(patient.getPatientname() + " 환자 진료 기록");
		patientNameLabel.setFont(new Font("맑은 고딕", Font.BOLD, 35));
		patientNameLabel.setBounds(60, 120, 500, 60);
		patientInformationPanel.add(patientNameLabel);

		JLabel patientPhoneNumberLabel = new JLabel("연락처) " + patient.getPhoneNumber());
		patientPhoneNumberLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
		patientPhoneNumberLabel.setBounds(500, 120, 300, 60);
		patientInformationPanel.add(patientPhoneNumberLabel);

		JPanel patientDetailInformationPanel = new JPanel();
		patientDetailInformationPanel.setVisible(true);
		patientInformationPanel.add(patientDetailInformationPanel);
		patientDetailInformationPanel.setBounds(60, 180, 1100, 120);

		JLabel getTreatmentTimeLabel = new JLabel("진료 일시");
		getTreatmentTimeLabel.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		getTreatmentTimeLabel.setPreferredSize(new Dimension(200, 50));
		patientDetailInformationPanel.add(getTreatmentTimeLabel);

		JLabel getTreatmentTime_OwnLabel = new JLabel(reservationDate + " " + reservationTime);
		getTreatmentTime_OwnLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
		getTreatmentTime_OwnLabel.setPreferredSize(new Dimension(300, 50));
		patientDetailInformationPanel.add(getTreatmentTime_OwnLabel);

		JLabel SymptomStartedTimeLabel = new JLabel();
		SymptomStartedTimeLabel.setPreferredSize(new Dimension(200, 50));
		patientDetailInformationPanel.add(SymptomStartedTimeLabel);

		JLabel SymptomstartedTime_OwnLabel = new JLabel();
		SymptomstartedTime_OwnLabel.setPreferredSize(new Dimension(300, 50));
		patientDetailInformationPanel.add(SymptomstartedTime_OwnLabel);

		JLabel treatmentSubjectLabel = new JLabel("진료 과목");
		treatmentSubjectLabel.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		treatmentSubjectLabel.setPreferredSize(new Dimension(200, 50));
		patientDetailInformationPanel.add(treatmentSubjectLabel);

		JLabel treatmentSubject_OwnLabel = new JLabel(
				thisReservation.getCareType() + ") " + thisReservation.getDocterName() + " 의사");
		treatmentSubject_OwnLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
		treatmentSubject_OwnLabel.setPreferredSize(new Dimension(300, 50));
		patientDetailInformationPanel.add(treatmentSubject_OwnLabel);

		JLabel symptomLabel = new JLabel("증상");
		symptomLabel.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		symptomLabel.setPreferredSize(new Dimension(200, 50));
		patientDetailInformationPanel.add(symptomLabel);

		String symptoms = "";
		for (String symptom : thisReservation.getSymptomList())
			symptoms += symptom + " ";
		JLabel symptom_OwnLabel = new JLabel(symptoms);
		symptom_OwnLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
		symptom_OwnLabel.setPreferredSize(new Dimension(300, 50));
		patientDetailInformationPanel.add(symptom_OwnLabel);

		// 두번째 -> 표
		String col[] = { "번호", "진료 의사", "진료 과목", "진료 일자", "진료 시간", "상세보기" };

		Object values[][] = patient.getReservationList();

		JTable patientInformationTable = new JTable(values, col);
		patientInformationTable.setRowHeight(40);

		// 테이블 내용 가운데 정렬
		DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();
		dtcr.setHorizontalAlignment(SwingConstants.CENTER);

		TableColumnModel tcm = patientInformationTable.getColumnModel();

		for (int i = 0; i < tcm.getColumnCount(); i++) {
			tcm.getColumn(i).setCellRenderer(dtcr);
		}

		JScrollPane pane = new JScrollPane(patientInformationTable);
		pane.setBounds(60, 350, 1100, 350);

		patientInformationPanel.add(pane);

		patientInformationTable.getColumnModel().getColumn(5).setCellRenderer(new TableCell());
		patientInformationTable.getColumnModel().getColumn(5).setCellEditor(new TableCell());

		this.add(patientInformationPanel);
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
