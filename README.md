# hhplus_tdd_task_02

# 특강 신청 서비스

- 클린 아키텍처를 이용해서 특강 신청 서비스를 구현한다.
- DB는 MSSQL 사용
- JDK 17

## 테이블 구조

![ERD.png](ERD.png)

```sql
-- 강의 기록 테이블
DROP TABLE hhplus.dbo.LECTURE_APPLY_HISTORY;

CREATE TABLE hhplus.dbo.LECTURE_APPLY_HISTORY(
	USER_ID BIGINT NOT NULL,
	LECTURE_ID BIGINT NOT NULL,
	CREATE_TIME DATETIME2,
	constraint LECTURE_APPLY_HISTORY_PK primary key(USER_ID, LECTURE_ID)
);

SELECT * FROM hhplus.dbo.LECTURE_APPLY_HISTORY;

-- 강의 테이블
DROP TABLE hhplus.dbo.LECTURE;

DROP SEQUENCE dbo.LECTURE_SEQ;

CREATE TABLE hhplus.dbo.LECTURE(
	ID BIGINT NOT NULL PRIMARY KEY,
	NAME nvarchar(300) NOT NULL,
	LECTURE_DATE DATETIME2 NOT NULL,
	UPDATE_TIME DATETIME2 NOT NULL
);

CREATE SEQUENCE dbo.LECTURE_SEQ
AS BIGINT
START WITH 1
INCREMENT BY 1;

SELECT * FROM hhplus.dbo.LECTURE;
```

> 해당 SQL은 MSSQL을 기준으로 작성되었습니다.

## 발제자님의 추가 의견

### 과제에 관한 것

- 유저 객체는 따로 만들지 않는다.

### 개발 시 진행 과정

요구사항 분석을 완료한 후 시작한다.

#### [ STEP1 ]

- API Spec -> MockAPI 만듬. (Service할 수 없는 상태)
  - ServiceIF(interface)를 딴다. (필요하다면)
  - 아니면 그냥 고정된 Response
- MockMvc 이용해서 MockAPI 정상동작 테스트 작성

> API Spec
> 
> e.g @GET(uri) request, response 같은 스웨거에서 나오는것을 미리 작성한다는 뜻이다.
> 
> 화면에서 게시글 목록을 내려줘야한다. 근데 상단에는 인기글 고정이 있어야한다.
> 1. 화면에 fit한 API
>    - 그냥 인기글 boolean으로 마킹해서 페이져블로 내려옴.
> 2. 범용성을 키우는 API
>    - 인기글 조회 API 시스템에서 정한 카운트만큼 내려줌.
>    - 게시글 목록 조회 API Pagealbe

#### [ STEP2 ]

- 비즈니스 로직을 설계 ( DB 신경 안씀 )
- 비즈니스 로직에서 필요한 외부 의존성에 대해 IF로 작업한다
- TDD로 개발 진행

#### [ STEP3 ]

- 비즈니스 로직의 외부의존 IF들에 대한 구현체를 작성
- DB, 외부 API, 파일 시스템 등...

#### [ STEP4 ]

- 다 역어서 의존성 제대로 붙는지
  - Spring이 울부짖음. (너 이거 Been 없어)
- 통합테스트
  - 전체 로직에 대해서 데이터 정합성이 맞는지
  - 동시성 문제가 제어되고 있는지, ...
- E2E
  - Filter/Interceptor의 동작까지 정상적으로 이루어지는지 테스트 작성.


