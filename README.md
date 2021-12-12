# Server

# Rest API

url: `http://18.222.61.22:8080/api`

## Common Request Header

```bash
Authorization Bearer {accessToken}
```

# Member / Auth

## 로그인 및 회원가입

### Get `/auth/login`

### Response

http code 200 인 경우 반환하는 데이터

| Field | Type | Description |
| --- | --- | --- |
| accessToken | String | jwt token |


# Wine

## 와인 등록

### Post `/wine`

### Request

| Field | Type | Description |
| --- | --- | --- |
| winecellarId | Long | 와인이 보관될 와인셀러 id |
| wineName | String | 와인의 이름 |
| location | Integer | 와인의 위치(1층, 2층) |
| labelImage | String | 사용자가 촬영한 와인의 라벨 이미지 url |
| vintage | Integer | 와인셀러에 적용되어 있는 내부 온도 정보 |
| purchaseDate | Timestamp | 해당 와인을 구매 한 날짜 |
| producedDate | Timestamp | 해당 와인이 생산 된 날짜 |

### Response

http code 200 인 경우 반환하는 데이터

| Field | Type | Description |
| --- | --- | --- |
| wineId | Long | 와인의 Id |
| winecellarId | Long | 와인이 보관될 와인셀러 id |
| wineName | String | 와인의 이름 |
| location | Integer | 와인의 위치(1층, 2층) |
| labelImage | String | 사용자가 촬영한 와인의 라벨 이미지 url |
| vintage | Integer | 와인셀러에 적용되어 있는 내부 온도 정보 |
| purchaseDate | Timestamp | 해당 와인을 구매 한 날짜 |
| producedDate | Timestamp | 해당 와인이 생산 된 날짜 |

## 와인 조회

### Get `/wine/{id}`

### Response

http code 200 인 경우 반환하는 데이터

| Field | Type | Description |
| --- | --- | --- |
| wineId | Long | 와인의 Id |
| winecellarId | Long | 와인이 보관될 와인셀러 id |
| wineName | String | 와인의 이름 |
| location | Integer | 와인의 위치(1층, 2층) |
| labelImage | String | 사용자가 촬영한 와인의 라벨 이미지 url |
| vintage | Integer | 와인셀러에 적용되어 있는 내부 온도 정보 |
| purchaseDate | Timestamp | 해당 와인을 구매 한 날짜 |
| producedDate | Timestamp | 해당 와인이 생산 된 날짜 |

## 와인 수정 - 히스토리로 변경

### Put `/wine/{id}`

### Request

| Field | Type | Description |
| --- | --- | --- |
| corkImage | String | 사용자가 촬영한 와인의 코르크 라벨 이미지 url |


# Winecellar

## 와인셀러 등록

### Post `/winecellar`

### Request

| Field | Type | Description |
| --- | --- | --- |
| serialNo | String | 소유한 와인셀러의 시리얼 번호 / 제품 번호 |

### Response

http code 200 인 경우 반환하는 데이터

| Field | Type | Description |
| --- | --- | --- |
| winecellarId | Long | id |
| type | String | 와인셀러의 종류 |
| nickName | String | 와인셀러의 별칭 |
| lock | Boolean | 와인셀러가 잠겨 있는지 여부 |
| lockPassword | String | 사용자가 설정한 비밀번호 |
| lightColor | String | 와인셀러에 적용되어 있는 조명 정보 |
| temperature | Integer | 와인셀러에 적용되어 있는 내부 온도 정보 |
| humidity | Integer | 와인셀러에 적용되어 있는 내부 습도 정보 |
| wineDtos | List<Wine> | 와인셀러에 보관 중인 와인  |

## 와인셀러 정보 조회

### Get `/winecellar`

### Response

http code 200 인 경우 반환하는 데이터

| Field | Type | Description |
| --- | --- | --- |
| winecellarId | Long | id |
| type | String | 와인셀러의 종류 |
| nickName | String | 와인셀러의 별칭 |
| lock | Boolean | 와인셀러가 잠겨 있는지 여부 |
| lockPassword | String | 사용자가 설정한 비밀번호 |
| lightColor | String | 와인셀러에 적용되어 있는 조명 정보 |
| temperature | Integer | 와인셀러에 적용되어 있는 내부 온도 정보 |
| humidity | Integer | 와인셀러에 적용되어 있는 내부 습도 정보 |
| wineDtos | List<Wine> | 와인셀러에 보관 중인 와인  |

## 와인셀러 정보 수정

### Put `/winecellar`

### Request

| Field | Type | Description |
| --- | --- | --- |
| winecellarId | Long | id |
| nickName | String | 와인셀러의 별칭 |
| lock | Boolean | 와인셀러가 잠겨 있는지 여부 |
| lockPassword | String | 사용자가 설정한 비밀번호 |
| lightColor | String | 와인셀러에 적용되어 있는 조명 정보 |
| temperature | Integer | 와인셀러에 적용되어 있는 내부 온도 정보 |
| humidity | Integer | 와인셀러에 적용되어 있는 내부 습도 정보 |

### Response

http code 200 인 경우 반환하는 데이터

| Field | Type | Description |
| --- | --- | --- |
| winecellarId | Long | id |
| type | String | 와인셀러의 종류 |
| nickName | String | 와인셀러의 별칭 |
| lock | Boolean | 와인셀러가 잠겨 있는지 여부 |
| lockPassword | String | 사용자가 설정한 비밀번호 |
| lightColor | String | 와인셀러에 적용되어 있는 조명 정보 |
| temperature | Integer | 와인셀러에 적용되어 있는 내부 온도 정보 |
| humidity | Integer | 와인셀러에 적용되어 있는 내부 습도 정보 |
| wineDtos | List<Wine> | 와인셀러에 보관 중인 와인  |
