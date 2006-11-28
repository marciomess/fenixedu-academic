--  SQL file representing changes to the functionalities model
--  Generated at Tue Nov 28 17:47:17 WET 2006
--  DO NOT EDIT THIS FILE, run the generating script instead

--  Preamble
SET AUTOCOMMIT = 0;

START TRANSACTION;

-- 
--  Updating existing functionalities
-- 

--  ID: 48 UUID: 'a219a6ee-798a-43ed-928f-d071c419a6a6'
UPDATE `ACCESSIBLE_ITEM` AS own SET own.`NAME` = 'pt31:Edi��o de Informa��o Curricularen30:Editing Curricular Information' WHERE own.`UUID` = 'a219a6ee-798a-43ed-928f-d071c419a6a6';

--  ID: 125 UUID: '3a42f8f8-61c9-42c7-89eb-4afec28702fc'
UPDATE `ACCESSIBLE_ITEM` AS own SET own.`ORDER_IN_MODULE` = 4 WHERE own.`UUID` = '3a42f8f8-61c9-42c7-89eb-4afec28702fc';

--  ID: 126 UUID: '4ec98293-0467-4234-bca2-c157e1492962'
UPDATE `ACCESSIBLE_ITEM` AS own SET own.`ORDER_IN_MODULE` = 5 WHERE own.`UUID` = '4ec98293-0467-4234-bca2-c157e1492962';

--  ID: 127 UUID: '89ad30c1-3b3f-4130-8071-0cad81f14648'
UPDATE `ACCESSIBLE_ITEM` AS own SET own.`NAME` = 'pt16:Cargos de Gest�o', own.`ORDER_IN_MODULE` = 6 WHERE own.`UUID` = '89ad30c1-3b3f-4130-8071-0cad81f14648';

--  ID: 128 UUID: '9c6f626c-1541-4375-9508-b8314cd4f210'
UPDATE `ACCESSIBLE_ITEM` AS own SET own.`NAME` = 'pt16:Cargos de Gest�o', own.`ORDER_IN_MODULE` = 7 WHERE own.`UUID` = '9c6f626c-1541-4375-9508-b8314cd4f210';

--  ID: 129 UUID: 'd967c2bb-fa88-45d2-bd05-666280ec416d'
UPDATE `ACCESSIBLE_ITEM` AS own SET own.`NAME` = 'pt16:Cargos de Gest�o', own.`ORDER_IN_MODULE` = 8 WHERE own.`UUID` = 'd967c2bb-fa88-45d2-bd05-666280ec416d';

--  ID: 130 UUID: '8f7d1dc2-9155-47c9-b142-19c545390907'
UPDATE `ACCESSIBLE_ITEM` AS own SET own.`ORDER_IN_MODULE` = 2 WHERE own.`UUID` = '8f7d1dc2-9155-47c9-b142-19c545390907';

--  ID: 252 UUID: '9cf56518-f706-4854-b28e-2108ba374c43'
UPDATE `ACCESSIBLE_ITEM` AS own SET own.`NAME` = 'pt11:Comunica��oen9:Messaging' WHERE own.`UUID` = '9cf56518-f706-4854-b28e-2108ba374c43';

--  ID: 253 UUID: '178d66a1-e8cf-4bb4-8278-5a453f024639'
UPDATE `ACCESSIBLE_ITEM` AS own SET own.`NAME` = 'pt16:Cargos de Gest�o', own.`ORDER_IN_MODULE` = 9 WHERE own.`UUID` = '178d66a1-e8cf-4bb4-8278-5a453f024639';

--  ID: 254 UUID: 'abc88970-ebe8-4a4d-9df1-fc45e27fb025'
UPDATE `ACCESSIBLE_ITEM` AS own SET own.`NAME` = 'pt16:Cargos de Gest�o', own.`ORDER_IN_MODULE` = 10 WHERE own.`UUID` = 'abc88970-ebe8-4a4d-9df1-fc45e27fb025';

--  ID: 255 UUID: '69f1121b-9917-47ae-ad00-d36f701c101f'
UPDATE `ACCESSIBLE_ITEM` AS own SET own.`NAME` = 'pt16:Cargos de Gest�o', own.`ORDER_IN_MODULE` = 11 WHERE own.`UUID` = '69f1121b-9917-47ae-ad00-d36f701c101f';

--  ID: 18953 UUID: '40bcc4e0-1d8e-4a02-8fca-5e6ef6e923f7'
UPDATE `ACCESSIBLE_ITEM` AS own, `ACCESSIBLE_ITEM` AS parent SET own.`KEY_MODULE` = parent.`ID_INTERNAL`, own.`NAME` = 'pt30:Criar Pessoa Externa/Convidada', own.`ORDER_IN_MODULE` = 0 WHERE own.`UUID` = '40bcc4e0-1d8e-4a02-8fca-5e6ef6e923f7' AND parent.`UUID` = ;

-- 
--  Inserting new functionalities
-- 

--  ID: 19090 UUID: '149921ea-c280-4c07-985e-f21ef612e37d'
INSERT INTO `ACCESSIBLE_ITEM` (`UUID`, `OJB_CONCRETE_CLASS`, `KEY_ROOT_DOMAIN_OBJECT`, `KEY_PARENT`, `KEY_MODULE`, `KEY_AVAILABILITY_POLICY`, `NAME`, `TITLE`, `DESCRIPTION`, `PATH`, `PREFIX`, `RELATIVE`, `ENABLED`, `PARAMETERS`, `ORDER_IN_MODULE`, `VISIBLE`, `MAXIMIZED`, `PRINCIPAL`, `KEY_SUPERIOR_SECTION`, `SECTION_ORDER`, `KEY_SITE`, `LAST_MODIFIED_DATE_YEAR_MONTH_DAY`, `KEY_SECTION`, `ITEM_ORDER`, `INFORMATION`) SELECT '149921ea-c280-4c07-985e-f21ef612e37d', 'net.sourceforge.fenixedu.domain.functionalities.ConcreteFunctionality', 1, NULL, `ID_INTERNAL`, NULL, 'pt13:Editar Pessoa', NULL, NULL, '/editPerson.do?method=prepareSearchPersonToEdit', NULL, 1, 1, NULL, 3, 1, 0, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL FROM `ACCESSIBLE_ITEM` WHERE `UUID` = 'cced3f10-5a09-470a-bfed-aed2454805bf';

--  ID: 19109 UUID: '9c631a37-f53d-486a-abff-298bbac2373c'
INSERT INTO `ACCESSIBLE_ITEM` (`UUID`, `OJB_CONCRETE_CLASS`, `KEY_ROOT_DOMAIN_OBJECT`, `KEY_PARENT`, `KEY_MODULE`, `KEY_AVAILABILITY_POLICY`, `NAME`, `TITLE`, `DESCRIPTION`, `PATH`, `PREFIX`, `RELATIVE`, `ENABLED`, `PARAMETERS`, `ORDER_IN_MODULE`, `VISIBLE`, `MAXIMIZED`, `PRINCIPAL`, `KEY_SUPERIOR_SECTION`, `SECTION_ORDER`, `KEY_SITE`, `LAST_MODIFIED_DATE_YEAR_MONTH_DAY`, `KEY_SECTION`, `ITEM_ORDER`, `INFORMATION`) SELECT '9c631a37-f53d-486a-abff-298bbac2373c', 'net.sourceforge.fenixedu.domain.functionalities.Module', 1, `ID_INTERNAL`, `ID_INTERNAL`, NULL, 'pt27:Pessoas Externas/Convidadas', NULL, NULL, '', '/', 1, 1, NULL, 12, 1, 0, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL FROM `ACCESSIBLE_ITEM` WHERE `UUID` = 'cced3f10-5a09-470a-bfed-aed2454805bf';

--  ID: 19129 UUID: 'f1c8b3cf-3212-498e-ab7a-8acaadffa902'
INSERT INTO `ACCESSIBLE_ITEM` (`UUID`, `OJB_CONCRETE_CLASS`, `KEY_ROOT_DOMAIN_OBJECT`, `KEY_PARENT`, `KEY_MODULE`, `KEY_AVAILABILITY_POLICY`, `NAME`, `TITLE`, `DESCRIPTION`, `PATH`, `PREFIX`, `RELATIVE`, `ENABLED`, `PARAMETERS`, `ORDER_IN_MODULE`, `VISIBLE`, `MAXIMIZED`, `PRINCIPAL`, `KEY_SUPERIOR_SECTION`, `SECTION_ORDER`, `KEY_SITE`, `LAST_MODIFIED_DATE_YEAR_MONTH_DAY`, `KEY_SECTION`, `ITEM_ORDER`, `INFORMATION`) SELECT 'f1c8b3cf-3212-498e-ab7a-8acaadffa902', 'net.sourceforge.fenixedu.domain.functionalities.ConcreteFunctionality', 1, NULL, `ID_INTERNAL`, NULL, 'pt18:Gest�o de Convites', NULL, NULL, '/invitationsManagement.do?method=prepareSearchPersonForManageInvitations', NULL, 1, 1, NULL, 1, 1, 0, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL FROM `ACCESSIBLE_ITEM` WHERE `UUID` = '9c631a37-f53d-486a-abff-298bbac2373c';

COMMIT;
