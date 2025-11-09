SELECT hd.dept_id, hd.DEPT_NAME_EN, ROUND(SUM(he.sal) / COUNT(he.sal),0) AS AVG_SAL
FROM HR_DEPARTMENT as hd
JOIN HR_EMPLOYEES as he ON hd.dept_id = he.dept_id
GROUP BY hd.dept_id
ORDER BY AVG_SAL desc
