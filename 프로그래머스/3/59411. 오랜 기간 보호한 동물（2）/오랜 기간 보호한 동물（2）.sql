-- 코드를 입력하세요
select ains.animal_id,ains.name
from animal_ins as ains
INNER JOIN animal_outs as aout ON aout.animal_id = ains.animal_id
ORDER BY (aout.datetime - ains.datetime) desc
limit 2

