## ProductServer

<hr>

해당 브랜치의 경우에는 View 없이 RestAPI로 구성

<hr>

### API
|FUCNTION|METHOD|URI|PARAM|BODY|DESC|
|--------|------|---|-----|----|----|
|로그인|POST|/login|-|userId, userPassward|기본적인 기능 수행을 위한 로그인|
|로그아웃|GET|/logout|-|userId, userPassward|명시적인 세션 만료를 위한 로그아웃|
|회원가입|POST|/register|-|userId, userPassward, **userType**|고객 등록을 위한 회원가입|
|상품목록조회|GET|/productList|**language**, **status**|-|현재 등록되어 있는 상품의 목록 조회 <br> EDITOR의 경우에만 Status 조정|
|상품상세조회|GET|/product|productId|-|상품의 ID를 통해 내부 내용 조회|
|상품정보조회|GET|/auth/productInfo|productInfoId|-|하나의 상품에 등록된 언어 별 게시물 확인 목적|
|상품정보목록조회|GET|/auth/productInfoList|productInfoId|-|각 상품 별 언어 정보 등록 현황 확인 목적|
|상품정보등록|POST|/auth/productInfo|-|ProductRequestDTO|WRITER가 상품을 등록 (title, contents, price, language)|
|상품정보수정|PATCH|/auth/productInfo|productInfoId|ProductInfo|Status 및 수수료 수정|
|상품등록|POST|/auth/product|-|Product|EDITOR가 언어에 따른 Product를 추가|
|상품수정|PATCH|/auth/product|productId|Product|상품 내용 / Title / Price 수정|

<br>
userType : WRITER, EDITOR, CUSTOMER <br>
Language : KOR, ENG, CHN <br>
Status : WAITING(Writer가 검토 요청 보내기 전), WAITING_CONSIDER(검토 요청 대기), UNDER_CONSIDER(Editor가 검토 진행 중), SELLING(Editor 검토 완료), REFUSED, SUSPENDED
