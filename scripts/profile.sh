#!/usr/bin/env bash

# 쉬고 있는 profile 찾기 : reail1이 사용 중이면 real2가 쉬고 있고, 반대면 real1이 쉬고 있음
function find_idle_profile() {
  # 현재 ㅇ네진엑스가 바라보고 있는 스프링부트가 정상적으로 수행중인지 확인한다.
  RESPONSE_CODE=$(curl -s -o /dev/null -w "%{http_code}" http://localhost/profile)

  if [ ${RESPONSE_CODE} -ge 400 ] # 400 보다 크면 (모든 에러를 포함)
  then
    CURRENT_PROFILE=real2
  else
    CURRENT_PROFILE=$(curl -s http://localhost/profile)
  fi

  if [ ${CURRENT_PROFILE} == real1 ]
  then
    IDLE_PROFILE=real2
  else
    IDLE_PROFILE=real1
  fi

  # bash 스크립트는 값을 반환하는 기능이 없으므로 결과를 출력후 클라이언트에서 값을 잡아서 받도록 한다.
  echo "${IDLE_PROFILE}"
}

# 쉬고있는 profile의 port 찾기
function find_idle_port() {
  IDLE_PROFILE=$(find_idle_profile)

  if [ ${IDLE_PROFILE} == real1 ]
  then
    echo "8081"
  else
    echo "8082"
  fi
}