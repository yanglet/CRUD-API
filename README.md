# CRUD-API
CRUD-API

## 구현할 기능
```
- 상품 등록 API (단건)
- 상품 조회 API (여러건, 페이징)
- 상품 수정 API (단건)
- 상품 재고 차감 API (단건)
- 상품 삭제 API (단건)
- 테스트 코드
```

## 작은 이슈들? 조금이라도 고민한 것?
```
- requestBody 로 받을 때 생성자 사용안하므로 PRIVATE 로 두어도 됨 (리플렉션 사용)
- response, request 는 getter 가 필요하다. MappingJackson2HttpMessageConverter 가 getter 를 사용함
getter 가 없을 시 406 not acceptable 내뱉음
- domain 에 pojo 를 두면 좋겠지만 jpa 의 이점을 제대로 사용 못하는 것 같아서 entity 를 두었음
- controller 단에서 validation vs service 에서 validation
- command 와 query 를 분리하지 않으면 repository 구현체가 더러워지는듯한 느낌이 듦,,
(querydsl + jpa) api에 핏한 애들은 분리하는게 좋은가
- 테스트 코드를 작성하기 쉽도록 하려면 service 단에서 dto 로 받지않는게 좋은듯.. 근데 파라미터가 많으면? ,,
- 전략 패턴의 Strategy 부분은 어떻게 네이밍 하는게 좋을까
- dto 는 하나의 클래스에 스태틱으로 여러개 만드는게 좋은가 아니면 다 분리하는게 좋은가
(ex ItemDto.CREATE, ItemDto.UPDATE 또는 ItemCreateRequest, ItemUpdateRequest)
```

## 다시 보자
```
- redisson pub/sub 분산락 적용하는데 더티 체킹으론 불가능 .. ? flush 꼭 해줘야하나
```