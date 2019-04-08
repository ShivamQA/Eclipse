Page Title: Email Alerts (ACS Publications)

# Object Definitions
==================================================================================================================
lnk_navigationPanel            0         xpath               //div[@id='sideNav']//ul/li//span[contains(text(),'${link}')]/parent::a
txt_linkHeading                0         xpath               //h1[contains(text(),'${heading}')]
txt_alertsInstructionMsg       0         xpath               //h1[text()='E-Mail Alerts']/following-sibling:: p[1]
lnk_citationAlerts             0         xpath               //a[text()='Citation Alerts']
tab_emailAlertPage             0         css                 li[role=tab]>a[title='${tab}']
txt_journalAlerts              0         css                 .registerAlerts >form>h2
txt_journalAlertOptions        0         css               .registerAlerts >form>p
btn_addJournal                 0         css                 .registerAlerts >form>p>input
tbl_contentAlert               0         css                 #journalsAlertsTable .contentsTableHeader
txt_noJournalSelected          0         css                 #journalsAlertsTable .nojournalselected
txt_selectEmailFormateAlerts   0         css                 .registerAlerts >form>h3
label_emailFormate             0         css                 .registerAlerts >form>div>label>span
btn_savePreferences            0         css                 input[value='Save Preferences']
txt_citationAlert              0         css                 .citationsPanel>h2
txt_defaultCitationMsg         0         css                 .citationsPanel>p:nth-child(3) 
popUp_allJournals              0         xpath                 //div[@class='journalsModalWrap all-journals']
count_allJournals              0         xpath                 //div[@id='jmodal-main']//a 
txt_omegaJournal               0         id                    journal-acsodf
btn_closePopUp                 0         css                   #jmodal-close_btn   
chkbx_issueAlert               0         xpath                //td[text()='${journal}']/..//span[text()='Issue alerts:']/following-sibling::input  
txt_selectedTitle              0         css                 .journalTitle
select_dd_articleAlert         0         xpath               //td[text() ='${journal}']/..//span[text()='Article alerts:']/following-sibling::select
dd_articleAlert                0         xpath               //td[text() ='${journal}']/..//span[text()='Article alerts:']/following-sibling::select/option
txt_emailAlertUpdate           0         css                 .success>p
h1_EmailALerts			       0		 xpath				//div[contains(text(),"E-Mail Alerts")]
link_EmailAlerts		       0		 xpath				//span[text()="E-Mail Alerts"]/parent::a
btn_AddJournal			       0		 css				[value='Add Journal']
==================================================================================================================
