# Redis+Kafka 로 만드는 선착순 쿠폰 시스템
- "실습으로 배우는 선착순 이벤트 시스템" 인프런 강의를 참고
  - URL : https://inf.run/KGF4b

# 쿠폰 발급 시스템을 API 서버로 만들기
## POST /apply
### String memberId
- 유저가 쿠폰 발급함
- 유저는 한 쿠폰만 발급 가능
- 