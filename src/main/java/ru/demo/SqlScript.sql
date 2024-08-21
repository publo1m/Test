-- Запрос на SQL
-- Ответ:

SELECT rp.familyName, rp.givenName, rp.middleName, rp.birthDate, pd.contactRelationship
FROM HPPersonGeneric pg
         JOIN HPPersonDependant pd ON pg.sysId = pd.HPPersonGenericSysId
         JOIN HPPersonGeneric rp ON pd.HPRelatedPersonSysId = rp.sysId
WHERE pg.personId = 'test'