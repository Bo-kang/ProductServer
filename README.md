## ProductServer

<hr>

###개요
개발 환경 : Windows10 <br>
언어 : Java (Spring Boot) <br>
DB : H2 Database <br>
View : Thymeleaf <br> <br>
특이사항 : Rest API 보다는 Front를 통해서 직관적인 결과를 확인하기 용이하도록 개발 진행 <br><br>

보완 필요 사항
1. 보안관련 고려가 되어있지 않음 (PW를 DB에 평문을 저장)
2. 실시간으로 환율을 받아오지 못함
3. UserType 별 호출 가능한 URL에 대한 제약 지정 필요
4. 코드 구조 개선 필요 ( Controller 분리 및 Service 기능 분할 )



<br>

###기능
| 구현된 기능                 | Desc                                                             | 제약사항                                                |
|------------------------|------------------------------------------------------------------|-----------------------------------------------------|
| 로그인                    | 로그인 한 유저에 대하여 등록여부 판단 후 Session에 Member 저장                       | 현재 비밀번호 암호화에 대한 고려가 이루어져있지 않음                       |
| 로그아웃                   | 로그인 되어 있는 유저에 대한 세션 만료                                           |
| ProductList 조회         | Editor의 검토가 완료되어 판매중인 제품 목록 표시                                   |                                                     ||
| 언어에 따른 Product List 조회 | 언어 설정을 통해 해당 언어로 등록되어 있는 Product List 표시                         | 현재 언어 설정에 대한 Cookie / Session 유지에 대한 고려가 이루어져있지 않음 (Page 이동 시 Default 상태로 변경) |
| 상태에 따른 Product List 조회 | 상태 설정을 통해 해당 상태로 등록되어 있는 Product List 표시 ( Editor )              | 현재 상태 설정에 대한 Cookie / Session 유지에 대한 고려가 이루어져있지 않음  |
| Product 등록             | Writer가 Product를 등록                                              ||
| 상태 변경                  | Writer - 검토요청, Editor - 검토 진행 중, 판매 중 상태로 변경                     ||
| Product 수정             | Editor가 검토 요청 상태의 Product를 수정하거나 언어별 Title, Contents 추가 및 수수료 등록 |                                   |
