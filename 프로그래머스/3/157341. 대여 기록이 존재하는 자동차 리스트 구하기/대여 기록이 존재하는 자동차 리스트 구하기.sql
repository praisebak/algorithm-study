-- 코드를 입력하세요
SELECT DISTINCT crh.car_id
from CAR_RENTAL_COMPANY_RENTAL_HISTORY crh
LEFT OUTER JOIN CAR_RENTAL_COMPANY_CAR crc ON crc.car_id = crh.car_id
WHERE crc.car_type = '세단' 
  AND crh.start_date LIKE '%-10-%'
ORDER BY crh.car_Id DESC