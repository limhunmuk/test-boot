# 영동 CMS 메뉴 이식

풍동 eGov 5 환경을 유지하고 영동의 메뉴 계층, 설명, 사용/노출, 정렬 관리 기능을 적용한다.
영동 데이터셋 담당자 권한 및 SSO는 이식하지 않고 기존 Spring Security 인증을 사용한다.

## DB 적용

기존 PostgreSQL DB에는 `src/main/resources/db/migration/cms-menu-postgres.sql`을 한 번 적용한다.
자동 실행하지 않는다. 먼저 백업하고 menu_key 중복을 정리해야 고유 인덱스가 생성된다.
기존 메뉴는 상위 메뉴가 없는 상태로 유지되며 사용/노출은 Y가 된다.
신규 DB용 참고 스키마에는 변경 내용을 반영했다. 참고 스키마의 DROP 문을 기존 DB에 실행하지 않는다.

## 공통 Mapper

`CommonMapper`는 `SqlSessionTemplate`을 사용한다. 메뉴 SQL namespace는 `CmsMenu`이다.
SQL ID는 Service의 상수로 선택하며 사용자 요청에서 받지 않는다.
파라미터는 DTO/VO/Map을 사용할 수 있고 다중 변경의 트랜잭션 경계는 Service에 둔다.
기존 회원/게시물 등 Mapper는 유지하며 이후 이식할 CMS 업무는 공통 Mapper를 사용한다.

## 인증과 샘플

로그인/정적 파일/오류 경로 외에는 인증이 필요하고 `/admin/**`는 ADMIN만 접근한다.
기존 BCrypt 로그인과 CSRF 보호를 유지한다. 메뉴의 노출 설정은 업무 URL의 권한 정책을 대체하지 않는다.
URL 메뉴 연결 화면은 비노출/미사용/권한 없는 메뉴를 차단한다.

LoggingFilter: 서버에서 생성한 요청 ID를 속성, MDC, X-Request-ID 헤더에 넣고 처리 시간을 기록한다.
CmsContextInterceptor: 관리자 MVC 요청에 cmsActor, cmsRequestPath를 제공하는 샘플이다.
LoggingInterceptor 및 기존 IP 접근 제어는 유지한다. 비밀번호/요청 본문을 로그에 추가하지 않는다.

## 확인 항목

- 비로그인 요청의 로그인 이동, USER의 관리자 접근 차단, ADMIN 메뉴 CRUD
- 상위/하위 정렬, 부모 숨김 시 자식 숨김, 사용/노출 설정
- 중복 키, 없는 부모, 자기/하위 부모 지정 거절, 하위 메뉴가 있는 부모 삭제 거절
- 파일/회원/게시물 등 기존 기능의 인증 후 접근

게시판 관리와 데이터 이전은 이번 메뉴 이식 범위에 포함하지 않는다.
