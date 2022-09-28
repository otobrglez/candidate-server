#!/usr/bin/env bash
SERVER="localhost:8000"
EVENT_URL="/event"
USER_URL="/user"

event() {
  name=$1
  user_id=$2
  properties=$3
  timestamp="${4:-$(date +%s)}"
  curl "${SERVER}${EVENT_URL}" \
    -H 'content-type: application/json' \
    -d "{\"name\": \"${name}\", \"properties\": \"${properties}\", \"timestamp\": \"${timestamp}\", \"user_id\": \"${user_id}\"}"
}

user() {
  user_id=$1
  properties=$2
  timestamp="${3:-$(date +%s)}"
  curl "${SERVER}${USER_URL}" \
    -H 'content-type: application/json' \
    -d "{\"user_id\": \"${user_id}\", \"properties\": \"${properties}\", \"timestamp\": \"${timestamp}\"}" \
    && echo # server doesn't return trailing newline
}

event_names=("Button Clicked" "Friend Invited" "Cart Viewed" "Checkout Entered" "Purchase Made" "User Login")
rand_event_name() {
  echo "${event_names[$RANDOM % ${#event_names[@]}]}"
}

rand_user() {
  echo "user_$((RANDOM % 10))"
}

event_properties=(
  "{'location': 'sidebar'}"
  "{'location': 'footer'}"
  "{'location': 'header'}"
  "{'tot_count': 4}"
  "{'tot_count': 44}"
  "{'tot_count': 49}"
  "{'num_items': 8}"
  "{'num_items': 0}"
  "{'num_items': 434}"
  "{'region': 'Americas'}"
  "{'region': 'Europe'}"
  "{'region': 'Africa'}"
  "{'latitude': 40.34, 'longitude': -120.45}"
  "{'latitude': 20.34, 'longitude': -0.45}"
  "{'latitude': 49.34, 'longitude': 110.45}"
  "{'x': 1234, 'y': 854}"
  "{'x': 34, 'y': 54}"
  "{'x': 1034, 'y': 3854}"
  "{'region': 'Americas', 'country': 'Canada'}"
  "{'region': 'Africa', 'country': 'Rwanda'}"
  "{'region': 'Australia', 'country': 'New Zealand'}"
)
rand_event_properties() {
  echo "${event_properties[$RANDOM % ${#event_properties[@]}]}"
}

user_properties=(
  "{'num_logins': 24}"
  "{'num_logins': 13}"
  "{'num_logins': 18}"
  "{'lifetime_val': 12984}"
  "{'lifetime_val': 49}"
  "{'lifetime_val': 12999984}"
  "{'first_name': 'John', 'last_name': 'Smith'}"
  "{'first_name': 'John', 'last_name': 'Jones'}"
  "{'first_name': 'Will', 'last_name': 'Smith'}"
  "{'region': 'Americas', 'country': 'Canada'}"
  "{'region': 'Americas', 'country': 'USA'}"
  "{'region': 'Europe', 'country': 'Lithuania'}"
)
rand_user_properties() {
  echo "${user_properties[$RANDOM % ${#user_properties[@]}]}"
}

seed() {
  # shellcheck disable=SC2034
  for _i in {1..2000}; do
    [[ $((RANDOM % 2)) -eq 1 ]] && event "$(rand_event_name)" "$(rand_user)" "$(rand_event_properties)"
    [[ $((RANDOM % 4)) -eq 1 ]] && user "$(rand_user)" "$(rand_user_properties)"
  done
  true
}

seed
#event "$(rand_event_name)" "$(rand_user)" "$(rand_event_properties)"
#user "$(rand_user)" "$(rand_user_properties)"
