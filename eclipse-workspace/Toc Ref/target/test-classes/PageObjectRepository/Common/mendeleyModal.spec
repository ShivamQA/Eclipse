List Of Issue Page

# Object Definitions
==================================================================================================================
btn_Mendeley				0				css						.achs-addto-mendeley a[title*='${State}']
txt_AddTo					0				css						.achs-addto-mendeley>span[class*='${State}']
modal_Mendeley				0				css						.achs-mendeley_container
texts_PairingScreen  		0				css						.achs-mendeley_dropzone1_state${stateNumber}>p
btn_Login					0				xpath					//h6[text()="${Step}"]/following-sibling::button[contains(@class,"%{Domain}")]
link_CreateMendeley			0				xpath					//h6[text()="STEP 2:"]/following-sibling::a[contains(@title,'Create a Mendeley')]
text_CreateACS				0				xpath					//h6[text()="STEP 1:"]/following-sibling::span[contains(text(),'create an ACS ID')]
StepsBridgeArrow			0				css						.icon-if-arrow-right
text_ScreenBottom			0				css						.achs-mendeley_dropzone2_state${stateNumber}>p
icon_Modalclose				0				css						.achs-mendeley_close
txt_ErrorMsg				0				xpath					//h1[text()="OOPS"]/following-sibling::p
btnACSLogin_ErrorMsg		0				xpath					//h1[text()="OOPS"]/following-sibling::button[text()="Login with ACS ID"]
IconClose_ErrorMsg			0				css 					.achs-mendeley_alert .icon-close
img_AuthorizationPage		0				css						img[title='Mendeley Authorization']
text_MendeleyloginModal		0				css						.scopes
text_Loginfield				0				css						#login .fields div>label[for='${Label}']
input_Loginfield			0				css						#login .fields div>input[name='${Label}']
btn_Authorize				0				xpath					//button[text()="Authorize"]
btn_ForgotYourPassword		0				xpath					//a[text()="Forgot your password?"]
h1_PasswordReset			0				xpath					//h1[text()="Password reset"]
input_ForgotPassEmail		0				css						label[for='email']+input
btn_Continue				0				css						button[class='achs-mendeley_continue-btn']
Img_Elsevier				0				css						main#maincontent>img
text_Elsevier				0				css						main#maincontent>h${type}
input_Email					0				css						#maincontent #email
btn_Elsevier				0				css						#maincontent [name='${type}']
email_SignInElsevier		0				css						#emailLabel+p
InputField_Elsevier			0				css						#maincontent #${field}
btn_SignIn					0				css						#maincontent [value='signin']
link_AlreadyAccount			0				xpath					//a[text()="I already have an account"]
link_differentAccount		0				xpath					//a[text()="Sign in with a different account"]
link_ForgotPassword			0				xpath					//a[text()="Forgot password?"]
icon_CloseMendeley1			0				css						.welcome-screen .close-welcome
icon_CloseMendeley2			0				css						.left .js-close-bubble
list_DocumentsLibrary		0				css						.document-list li
checkBox_ArticleLibrary		0				css						.document-list li:nth-child(1)>.tick-indicator
icon_FavArticleLibrary		0				css						.document-list li:nth-child(1)>.starred-indicator>span
icon_ReadArticleLibrary	    0				css						.document-list li:nth-child(1) span[class='read-indicator']
icon_PDFArticleLibrary		0				css						.document-list li:nth-child(1) .file-indicator span[class*=pdf]
timeAdded_ArticleLibrary	0				css						.document-list li:nth-child(1) .added
Tab_DetailsLibrary			0				css						#document-details-view
Title_ArticleLibrary		0				css						.document-list li:nth-child(1)>.title
Metadata_ArticleLibrary		0				css						.document-list li:nth-child(1)>.metadata
Type_LibraryDetailsTab		0				css						#document-details-view .document-type
Title_LibraryDetailsTab		0				css						#document-details-view .document-title
Authors_LibraryDetailsTab	0				css						#document-details-view .document-authors>li
Journal_LibraryDetailsTab   0               css						#document-details-view .document-source
Details_LibraryDetailsTab	0				css						#document-details-view .document-details
Abstract_LibraryDetailsTab	0				css						.document-abstract-content
DOI_LibraryDetailsTab		0				css						#document-details-view .document-identifiers
pdf_LibraryDetailsTab		0				css						.document-file-list li>a[class*='${Type}']


