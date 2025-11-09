-- 코드를 입력하세요
SELECT outs.animal_id,outs.name
FROM animal_outs as outs
LEFT OUTER JOIN animal_ins as ins ON outs.animal_id = ins.animal_id
WHERE ins.animal_id IS null
ORDER BY ins.animal_id ASC