HospitalApplication 클래스는 GUI 클래스입니다.
HospitalService에서 주요 기능을 구현한 후
HospitalApplication에서 GUI를 연동할거예요.
Hospital은 로그인 후 객체 생성하면 됩니다.
Patient와 Reservation은 파일입출력을 통해 로컬 정보(내역)를 불러오고
로그인 후에는 소켓통신을 통해 서버로부터 정보(현황)를 불러옵니다.

데이터는 임의로 넣고나서 메소드 구현하시면 돼요.
나중에 데이터 형식을 정형화하겠습니다.

병원측 기능으로는
로그인, 로그아웃
접수 현황, 예약 현황, 납부 현황 보기
접수 내역, 납부 내역 보기
환자 정보 보기 (이름, 연락처) -> 관련 접수 내역 표시
접수 내역 검색 (검색어: 환자정보, 접수일자)

HospitalService 메소드 구현시
데이터 요청부분은 아래의 예시처럼 주석해놓고
나머지 작성하시면 됩니다.

public void searchHospital(String keyword) {
    // 병원리스트 요청 후 데이터 저장
    ArrayList<Hospital> hospitalList = new ArrayList<>();
    hospitalList.add(hospitalExample1);
    hospitalList.add(hospitalExample2);
    hospitalList.add(hospitalExample3);

    // 메소드 구현
    ...
    ...

}    