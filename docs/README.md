## 기능 요구 사항

### 각 팀 별 체스 말 개수

- [ ] 폰 : 8개
- [ ] 룩 : 2개
- [ ] 비숍 : 2개
- [ ] 나이트 : 2개
- [ ] 킹 / 퀸 : 1개

### 시작 조건

- [ ] start를 입력하면 게임이 실행된다.

### 게임 진행

- [ ] 흰색 턴으로 시작해 한턴씩 번갈아가면서 진행한다.

#### 체스판 초기화

- [ ] 체스판에서 말의 위치 값은 가로 위치는 왼쪽부터 a ~ h이고, 세로는 아래부터 위로 1 ~ 8로 구현한다.
- [ ] 블랙 : 대문자, 화이트 : 소문자

```markdown
RNBQKBNR 8 (rank 8)
PPPPPPPP 7
........ 6
........ 5
........ 4
........ 3
pppppppp 2
rnbqkbnr 1 (rank 1)

abcdefgh
```

### 체스 말의 이동

- [ ] `move source위치 target위치`를 실행해 이동한다.
- [ ] 이동 위치에 내 말이 있으면 이동할 수 없다.

| 종류     | 말                                                                                                      |
|--------|--------------------------------------------------------------------------------------------------------|
| 슬라이딩   |- 퀸(Queen) : 상하좌우, 대각선으로 이동 가능하다.<br /> - 룩(Rook) : 상하좌우로 이동 가능하다. <br /> - 비숍(Bishop) : 대각선으로 이동 가능하다. |
| 논 슬라이딩 |- 나이트(Knight) : 상하좌우 한 칸 이동 후 앞방향 대각선으로 이동 가능하다.<br /> - 킹(King) : 상하좌우, 대각선으로 한 칸만 이동 가능하다.|
|폰|- 처음 : 앞으로 2칸까지 갈 수 있다. (슬라이딩)<br />- 기본 : 앞으로 한칸만 갈 수 있다.<br />- 잡기 : 좌우 대각선으로 한 칸만 갈 수 있다.|


### 종료 조건

- [ ] end를 입력하면 게임이 종료된다.
