# android-calculator-mvp

### Step1. 모듈분리

요구사항 

- 계산기 앱의 핵심 비즈니스 로직을 담은 클래스와 코드들을 모두 domain 모듈로 분리한다.
- 관련된 테스트 케이스들도 모두 domain 모듈로 분리한다.
- domain 모듈은 순수 코틀린 모듈이어야 한다.
- app 모듈은 domain 모듈에 의존해야 한다.

### Step2. 계산기 MVP
- app 모듈을 MVP 패턴으로 리팩터링한다.
- Presenter의 모든 로직에 대한 단위 테스트를 구현한다.

