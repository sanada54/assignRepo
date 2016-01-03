Open Default Log as LOG
msg = "read data from workflow"
Output Message msg to Log LOG
// 
RegSeg = "$" + _CURRENTREGION + "$" + _CURRENTSEGMENTNAME
Output ALPHA RegSeg to Log LOG
BookingTypeInterviewID = _CODEID("BookingType\IV")
Output NUMERIC BookingTypeInterviewID to Log LOG
// 
path = "JOB1" + RegSeg
JobID = _VIEW(path,"ID")
UserID = _CURRENTUSER
Output NUMERIC UserID to Log LOG
path = "CANDIDATE" + RegSeg
CandidateID = _VIEW(path,"ID")
Output NUMERIC CandidateID to Log LOG
// 
path = "STARTDATE" + RegSeg
StartDate = _VIEW(path)
Output DATE StartDate to Log LOG
path = "ENDDATE" + RegSeg
EndDate = _VIEW(path)
Output DATE EndDate to Log LOG
// 
path = "INCWEEKENDS" + RegSeg
IncWeekEnds = _VIEW(path)
Output ALPHA IncWeekEnds to Log LOG
// 
path = "drop" + RegSeg
ContactID = _VIEW(path,"ID")
Output NUMERIC ContactID to Log LOG
// 
path = "CLIENT" + RegSeg
ClientID = _VIEW(path,"ID")
Output NUMERIC ClientID to Log LOG
// 
path = "INVOICEPOINT" + RegSeg
InvoicePointID = _VIEW(path,"ID")
Output NUMERIC InvoicePointID to Log LOG
// 
path = "STARTTIME" + RegSeg
StartTime = _VIEW(path)
Output TIME StartTime to Log LOG
path = "ENDTIME" + RegSeg
EndTime = _VIEW(path)
Output TIME EndTime to Log LOG
path = "BOX" + RegSeg
Overnight = _VIEW(path)
Output ALPHA Overnight to Log LOG
path = "bStr1" + RegSeg
BreakStart1 = _VIEW(path)
Output TIME BreakStart1 to Log LOG
path = "bStr2" + RegSeg
BreakStart2 = _VIEW(path)
Output TIME BreakStart2 to Log LOG
path = "bStr3" + RegSeg
BreakStart3 = _VIEW(path)
Output TIME BreakStart3 to Log LOG
path = "bStr4" + RegSeg
BreakStart4 = _VIEW(path)
Output TIME BreakStart4 to Log LOG
path = "bStr5" + RegSeg
BreakStart5 = _VIEW(path)
Output TIME BreakStart5 to Log LOG
// 
path = "bEnd1" + RegSeg
BreakEnd1 = _VIEW(path)
Output TIME BreakEnd1 to Log LOG
path = "bEnd2" + RegSeg
BreakEnd2 = _VIEW(path)
Output TIME BreakEnd2 to Log LOG
path = "bEnd3" + RegSeg
BreakEnd3 = _VIEW(path)
Output TIME BreakEnd3 to Log LOG
path = "bEnd4" + RegSeg
BreakEnd4 = _VIEW(path)
Output TIME BreakEnd4 to Log LOG
path = "bEnd5" + RegSeg
BreakEnd5 = _VIEW(path)
Output TIME BreakEnd5 to Log LOG
// 
// 
IF ((BreakStart1 == _EMPTY && BreakEnd1 != _EMPTY) || (BreakStart1 != _EMPTY && BreakEnd1 == _EMPTY))
          Close Log LOG
          // #1644 Break Start and Break End times must be entered
          Exit with status:1 and Message No: 1644 and Focus Window: 
IF ((BreakStart2 == _EMPTY && BreakEnd2 != _EMPTY) || (BreakStart2 != _EMPTY && BreakEnd2 == _EMPTY))
          Close Log LOG
          // #1644 Break Start and Break End times must be entered
          Exit with status:1 and Message No: 1644 and Focus Window: 
IF ((BreakStart3 == _EMPTY && BreakEnd3 != _EMPTY) || (BreakStart3 != _EMPTY && BreakEnd3 == _EMPTY))
          Close Log LOG
          // #1644 Break Start and Break End times must be entered
          Exit with status:1 and Message No: 1644 and Focus Window: 
IF ((BreakStart4 == _EMPTY && BreakEnd4 != _EMPTY) || (BreakStart4 != _EMPTY && BreakEnd4 == _EMPTY))
          Close Log LOG
          // #1644 Break Start and Break End times must be entered
          Exit with status:1 and Message No: 1644 and Focus Window: 
IF ((BreakStart5 == _EMPTY && BreakEnd5 != _EMPTY) || (BreakStart5 != _EMPTY && BreakEnd5 == _EMPTY))
          Close Log LOG
          // #1644 Break Start and Break End times must be entered
          Exit with status:1 and Message No: 1644 and Focus Window: 
// 
path = "CONS1" + RegSeg
CONS1 = _VIEW(path,"ID")
Output NUMERIC CONS1 to Log LOG
path = "CONS2" + RegSeg
CONS2 = _VIEW(path,"ID")
Output NUMERIC CONS2 to Log LOG
path = "CONS3" + RegSeg
CONS3 = _VIEW(path,"ID")
Output NUMERIC CONS3 to Log LOG
path = "CONS4" + RegSeg
CONS4 = _VIEW(path,"ID")
Output NUMERIC CONS4 to Log LOG
path = "CONS1ST" + RegSeg
CONS1ST = _VIEW(path)
Output NUMERIC CONS1ST to Log LOG
path = "CONS2ST" + RegSeg
CONS2ST = _VIEW(path)
Output NUMERIC CONS2ST to Log LOG
path = "CONS3ST" + RegSeg
CONS3ST = _VIEW(path)
Output NUMERIC CONS3ST to Log LOG
path = "CONS4ST" + RegSeg
CONS4ST = _VIEW(path)
Output NUMERIC CONS4ST to Log LOG
// =start==Modified by Oksana D, 13/06/12, Eventum #16549, Standard System
path = "EBC"+RegSeg
EBC = _VIEW(path)
Output ALPHA EBC to Log LOG
// =end==Modified by Oksana D, 13/06/12, Eventum #16549, Standard System
// 
path = "Notes"+RegSeg
Notes1 = _VIEW(path)
Output ALPHA Notes1 to Log LOG
// 
// START=== Modified by Alexander Bikov, 12/07/2013. Eventum # 24721. Clash 'Interview' for extend/fill/book/placement if calendar type equals interview type
path = "CLASHINTERVIEW" + RegSeg
ClashInterview = _VIEW(path)
Output ALPHA ClashInterview to Log LOG
// END === Modified by Alexander Bikov, 12/07/2013. Eventum # 24721
// 
// ==== Modified by Sergey D, eventum 36272, 07/07/2014
path = "EmpDD"+RegSeg
Employee = _VIEW(path,"ID")
Output NUMERIC Employee to Log Log
// ==== Modified by Sergey D, eventum 36272, 07/07/2014
// ==================Validations==================
// 
msg = "validation - mandatory fields"
Output Message msg to Log LOG
IF (CandidateID == _EMPTY || CandidateID == 0.0)
          Close Log LOG
          Exit with status:1 and Message No: 223 and Focus Window: 
Get Entity CandidateID as CandidatePO
          Entity Not Found
CandidateRole = CandidatePO.defaultRoleName()
CandidateStatusOriginal = CandidatePO.attribute("CAND_GEN.STATUS:REFERENCE")
Output NUMERIC CandidateStatusOriginal to Log LOG
CandidateAttr = CandidatePO.attribute("CAND_PREF.P_TEMP:REFERENCE")
IF (CandidateAttr == "N" || CandidateAttr ==_EMPTY)
          Close Log LOG
          Exit with status:1 and Message No: 372 and Focus Window: 
IF (StartDate == _EMPTY || EndDate == _EMPTY)
          Close Log LOG
          Exit with status:1 and Message No: 224 and Focus Window: 
IF (StartTime == _EMPTY || EndTime == _EMPTY)
          Close Log LOG
          Exit with status:1 and Message No: 225 and Focus Window: 
// 
IF (StartTime == EndTime)
          Close Log LOG
          Exit with status:1 and Message No: 273 and Focus Window: 
ASDpath = "STARTDATE" + RegSeg
AEDpath = "ENDDATE" + RegSeg
ASTpath = "STARTTIME" + RegSeg
AETpath = "ENDTIME" + RegSeg
Execute CreateAssignment_CheckDates_RS JobID, StartDate, EndDate, StartTime, EndTime, ASDpath,AEDpath,ASTpath,AETpath
ExecStatus = _EXECUTESTATUS
IF (ExecStatus == 1)
          Close Log LOG
          Exit with status:0 and Message No: and Focus Window: 
IF (IncWeekEnds == _EMPTY)
          IncWeekEnds = "N"
Execute GetDayOfWeek StartDate,StartDateDayNumber
Output ALPHA StartDateDayNumber to Log LOG
IF ((StartDateDayNumber == "6" || StartDateDayNumber == "7") && IncWeekEnds != "Y")
          // 1214 = Start Date can not be Weekend day
          Close Log LOG
          Exit with status:1 and Message No: 1214 and Focus Window: 
// END === Modified by Alexander Bikov, 08 December 2011. Eventum #12673 [Standard System - AWR]===
// 
path = "PAIDHRS"+RegSeg
PaidHours = _VIEW(path)
IF (PaidHours == _EMPTY || PaidHours== 0.0)
          Close Log LOG
          Exit with status:1 and Message No: 226 and Focus Window: 
SendDocsBy = _VIEW("SENDDOCSBY$W_TemporaryRegularBooking$W_TemporaryRegularBooking","ID")
IF (SendDocsBy == _EMPTY || SendDocsBy == 0.0)
          Close Log LOG
          Exit with status:1 and Message No: 227 and Focus Window: 
// 
LOOP ( Initialise rIndex = 1; Do While rIndex <=15 ; Increment rIndex By 1 )
          // ===============Pre Pay==============
          path_PrePay = "PAY" + rIndex + RegSeg
          PrePay = _VIEW(path_PrePay)
          Output NUMERIC PrePay to Log LOG
          // ===============Pre Charge============
          path_PreCharge = "CHARGE" + rIndex + RegSeg
          PreCharge = _VIEW(path_PreCharge)
          Output NUMERIC PreCharge to Log LOG
          IF (PrePay > PreCharge)
              _VIEW(path_PrePay) = _EMPTY
              _VIEW(path_PreCharge) = _EMPTY
              Close Log LOG
              // 1113 = Pre AWR Pay Rate must be less then Pre AWR Charge Rate
              Exit with status:1 and Message No: 1113 and Focus Window: 
// 
Total = 0
IF (CONS1 != 0.0)
          Total = Total + CONS1ST
IF (CONS2 != 0.0)
          Total = Total + CONS2ST
IF (CONS3 != 0.0)
          Total = Total + CONS3ST
IF (CONS4 != 0.0)
          Total = Total + CONS4ST
IF (CONS1 == _EMPTY)
          Exit with status:1 and Message No: 763 and Focus Window: 
IF (Total != 100)
          path = "CONS1ST" + RegSeg
          _VIEW(path) = ""
          path = "CONS2ST" + RegSeg
          _VIEW(path) = ""
          path = "CONS3ST" + RegSeg
          _VIEW(path) = ""
          path = "CONS4ST" + RegSeg
          _VIEW(path) = ""
          Exit with status:1 and Message No: 764 and Focus Window: 
// 
// =========================Availability Check===================
msg = "validation - candidate availability check"
Output Message msg to Log LOG
WorkDate = StartDate
WHILE( WorkDate <= EndDate)
          Output DATE WorkDate to Log LOG
          IF (1 == 1)
              IF (Overnight == "Y")
                  NextDate = WorkDate + _DAYS(1)
              ELSE
                  NextDate = WorkDate
              // START=== Modified by Alexander Bikov, 12/07/2013. Eventum # 24721. Clash 'Interview'' for extend/fill/book/placement if calendar type equals interview type
              Execute CheckCalendar CandidateID, WorkDate, NextDate, StartTime,EndTime, Clash, 0, BookingTypeInterviewID
              // End === Modidfied by Olga Malyshkina on 25/11/11; Eventum 12417; Standard System - AWR === Check on Overnight
              Output ALPHA Clash to Log LOG
              IF (Clash == "Y")
                  Close Log LOG
                  Exit with status:1 and Message No: 270 and Focus Window: 
              IF (Clash == "Interview" && ClashInterview != "Y")
                  Output ALPHA Clash to Log LOG
                  // 
                  path = "CLASHINTERVIEW"+ RegSeg
                  _VIEW(path) = "Y"
                  // 
                  Close Log LOG
                  Exit with status:1 and Message No: 1444 and Focus Window: 
          WorkDate = WorkDate+_DAYS(1)
candPayType = CandidatePO.attribute("CAND_PAYROLL.CONT_PAY_TYP:REFERENCE")
// 
path = "PAY1" + RegSeg
Pay1 = _VIEW(path)
path = "CHARGE1" + RegSeg
Charge1 = _VIEW(path)
IF (IncWeekEnds == "Y")
          daysPerWeek = 7
ELSE
          daysPerWeek = _EMPTY
Output NUMERIC daysPerWeek to Log LOG
Output NUMERIC Pay1 to Log LOG
Output NUMERIC Charge1 to Log LOG
// 
Execute MarginCalculation candPayType,Pay1,Charge1,PaidHours,daysPerWeek,margin,marginPerc,dProfit,wProfit
Output NUMERIC margin to Log LOG
Output NUMERIC marginPerc to Log LOG
Output NUMERIC dProfit to Log LOG
Output NUMERIC wProfit to Log LOG
// =end==Modified by Oksana D., 14/06/12, Eventum #16701, Standard System
// 
// =====Create a new entity playing Temporary regular assignment role=========
msg = "create assignment"
Output Message msg to Log LOG
Edit Entity JobID using JobPO to refer to it. Ignoring Ownership.
          Entity Not Found
          Entity Already Locked
JobRole = JobPO.defaultRoleName()
currency = JobPO.attribute("JOB_GEN.CURRENCY:REFERENCE")
Output NUMERIC currency to Log LOG
currency_Alpha = format(currency, "CODE")
Output ALPHA currency_Alpha to Log LOG
// 
AssignmentRole = "TMPR_ASSIGN"
AssignmentType = _codeID("Assignment Type\1")
// Start===== Modified by Anton M on 04.07.14; [RandstadJapanDev] Eventum ID 36243 ===== 
AWRExemptYN = _EMPTY
ReasonExempt_ID = _EMPTY
PrimJobCat_ID = _EMPTY
// 
path = "Mon" + RegSeg
Mon = _VIEW(path)
path = "Tue" + RegSeg
Tue = _VIEW(path)
path = "Wed" + RegSeg
Wed = _VIEW(path)
path = "Thu" + RegSeg
Thu = _VIEW(path)
path = "Fri" + RegSeg
Fri = _VIEW(path)
path = "Sat" + RegSeg
Sat = _VIEW(path)
path = "Sun" + RegSeg
Sun = _VIEW(path)
tmpWorkDate = StartDate
tmpEndDate = EndDate
Output DATE tmpWorkDate to Log LOG
Output DATE tmpEndDate to Log LOG
SHIFT_COUNT = 0
WHILE( tmpWorkDate <= tmpEndDate)
          Output DATE tmpWorkDate to Log LOG
          Execute GetDayOfWeek tmpWorkDate,DayOfWeek
          Output NUMERIC DayOfWeek to Log LOG
          Output ALPHA Mon to Log LOG
          Output ALPHA Tue to Log LOG
          Output ALPHA Wed to Log LOG
          Output ALPHA Thu to Log LOG
          Output ALPHA Fri to Log LOG
          Output ALPHA Sat to Log LOG
          Output ALPHA Sun to Log LOG
          IF ((DayOfWeek==1 && Mon == "Y") || (DayOfWeek==2 && Tue == "Y") || (DayOfWeek==3 && Wed == "Y") || (DayOfWeek==4 && Thu == "Y") || (DayOfWeek==5 && Fri == "Y") || (DayOfWeek==6 && Sat == "Y") || (DayOfWeek==7 && Sun == "Y"))
              SHIFT_COUNT = SHIFT_COUNT + 1
              Output NUMERIC SHIFT_COUNT to Log LOG
          // 
          tmpWorkDate = tmpWorkDate + _DAYS(1)
          Output DATE tmpWorkDate to Log LOG
// 
Output NUMERIC SHIFT_COUNT to Log LOG
IF (SHIFT_COUNT == 0)
          Exit with status:1 and Message No: 1693 and Focus Window: 
// 
// -------- END -------- by Denis D, 03.11.2014, Randstad, mandatory working days
// 
// 
// 
// End ===== Modified by Anton M on 04.07.14; [RandstadJapanDev] Eventum ID 36243 ===== 
Execute CreateAssignment TempRegularAssignmentID,AssignmentRole,CandidateID,JobID,ContactID,ClientID,InvoicePointID,StartDate,EndDate,StartTime,EndTime,AssignmentType,currency,PrimJobCat_ID,AWRExemptYN,ReasonExempt_ID,SameClientYN,QualWeeks,VerifiedYN, Employee, BreakStart1, BreakEnd1, BreakStart2, BreakEnd2, BreakStart3, BreakEnd3, BreakStart4, BreakEnd4, BreakStart5, BreakEnd5
Output NUMERIC TempRegularAssignmentID to Log LOG
Output NUMERIC JobID to Log LOG
Edit Entity TempRegularAssignmentID using TemporaryRegularAssigPO to refer to it
          Entity Not Found
          Entity Already Locked
          Entity Ownership Exception
// //
Set value for "ASSIG_GEN.CONS1:REFERENCE" in TemporaryRegularAssigPO to CONS1
          Invalid Attribute
          Attribute Not Found
          Invalid Property
          Invalid Occurrence
          Entity Ownership Exception
          Modify Attribute Exception
Set value for "ASSIG_GEN.CONS2:REFERENCE" in TemporaryRegularAssigPO to CONS2
          Invalid Attribute
          Attribute Not Found
          Invalid Property
          Invalid Occurrence
          Entity Ownership Exception
          Modify Attribute Exception
Set value for "ASSIG_GEN.CONS3:REFERENCE" in TemporaryRegularAssigPO to CONS3
          Invalid Attribute
          Attribute Not Found
          Invalid Property
          Invalid Occurrence
          Entity Ownership Exception
          Modify Attribute Exception
Set value for "ASSIG_GEN.CONS4:REFERENCE" in TemporaryRegularAssigPO to CONS4
          Invalid Attribute
          Attribute Not Found
          Invalid Property
          Invalid Occurrence
          Entity Ownership Exception
          Modify Attribute Exception
Set value for "ASSIG_GEN.CONS1_PERC:REFERENCE" in TemporaryRegularAssigPO to CONS1ST
          Invalid Attribute
          Attribute Not Found
          Invalid Property
          Invalid Occurrence
          Entity Ownership Exception
          Modify Attribute Exception
Set value for "ASSIG_GEN.CONS2_PERC:REFERENCE" in TemporaryRegularAssigPO to CONS2ST
          Invalid Attribute
          Attribute Not Found
          Invalid Property
          Invalid Occurrence
          Entity Ownership Exception
          Modify Attribute Exception
Set value for "ASSIG_GEN.CONS3_PERC:REFERENCE" in TemporaryRegularAssigPO to CONS3ST
          Invalid Attribute
          Attribute Not Found
          Invalid Property
          Invalid Occurrence
          Entity Ownership Exception
          Modify Attribute Exception
Set value for "ASSIG_GEN.CONS4_PERC:REFERENCE" in TemporaryRegularAssigPO to CONS4ST
          Invalid Attribute
          Attribute Not Found
          Invalid Property
          Invalid Occurrence
          Entity Ownership Exception
          Modify Attribute Exception
// 
// =start==Modified by Oksana D., 14/06/12, Eventum #16701, Standard System
Set value for "TASSIG_GEN.DPROFIT:ASSIGNMENT" in TemporaryRegularAssigPO to dProfit
          Invalid Attribute
          Attribute Not Found
          Invalid Property
          Invalid Occurrence
          Entity Ownership Exception
          Modify Attribute Exception
Set value for "TASSIG_GEN.WPROFIT:ASSIGNMENT" in TemporaryRegularAssigPO to wProfit
          Invalid Attribute
          Attribute Not Found
          Invalid Property
          Invalid Occurrence
          Entity Ownership Exception
          Modify Attribute Exception
Set value for "TASSIG_GEN.MARGIN_VAL:ASSIGNMENT" in TemporaryRegularAssigPO to margin
          Invalid Attribute
          Attribute Not Found
          Invalid Property
          Invalid Occurrence
          Entity Ownership Exception
          Modify Attribute Exception
Set value for "TASSIG_GEN.MARGIN_PCNT:ASSIGNMENT" in TemporaryRegularAssigPO to marginPerc
          Invalid Attribute
          Attribute Not Found
          Invalid Property
          Invalid Occurrence
          Entity Ownership Exception
          Modify Attribute Exception
// =end==Modified by Oksana D., 14/06/12, Eventum #16701, Standard System
// 
// Start===== Modified by Keisuke Yamamoto on 10.11.14; [RandstadJapanDev] Eventum ID 40312 ===== 
path = "PAYTYPE" + RegSeg
PayType_ID = _VIEW(path,"ID")
Output NUMERIC PayType_ID to Log Log
Set value for "ASSIG_FEE.PAYMENT_TYPE:REFERENCE" in TemporaryRegularAssigPO to PayType_ID
          Invalid Attribute
          Attribute Not Found
          Invalid Property
          Invalid Occurrence
          Entity Ownership Exception
          Modify Attribute Exception
// 
path = "PAYCHARGETYPE" + RegSeg
PayChargeType_ID = _VIEW(path,"ID")
Output NUMERIC PayChargeType_ID to Log Log
Set value for "ASSIG_FEE.PAYMT_CH_TYP:REFERENCE" in TemporaryRegularAssigPO to PayChargeType_ID
          Invalid Attribute
          Attribute Not Found
          Invalid Property
          Invalid Occurrence
          Entity Ownership Exception
          Modify Attribute Exception
// 
path = "BILLTYPE" + RegSeg
BillType_ID = _VIEW(path,"ID")
Output NUMERIC BillType_ID to Log Log
Set value for "ASSIG_FEE.BILLING_TYPE:REFERENCE" in TemporaryRegularAssigPO to BillType_ID
          Invalid Attribute
          Attribute Not Found
          Invalid Property
          Invalid Occurrence
          Entity Ownership Exception
          Modify Attribute Exception
// 
path = "BILLCHARGETYPE" + RegSeg
BillChargeType_ID = _VIEW(path,"ID")
Output NUMERIC BillChargeType_ID to Log Log
Set value for "ASSIG_FEE.BILL_CH_TYP:REFERENCE" in TemporaryRegularAssigPO to BillChargeType_ID
          Invalid Attribute
          Attribute Not Found
          Invalid Property
          Invalid Occurrence
          Entity Ownership Exception
          Modify Attribute Exception
// 
// End===== Modified by Keisuke Yamamoto on 10.11.14; [RandstadJapanDev] Eventum ID 40312 ===== 
// RS　要件に伴い、職歴項目追加設定2015/01/31************************************
// 当社フラグ
OUR_CARRER = "Y"
attr = "ASSIG_GEN.OUR_CAREER:REFERENCE"
Set value for attr in TemporaryRegularAssigPO to OUR_CARRER
          Invalid Attribute
          Attribute Not Found
          Invalid Property
          Invalid Occurrence
          Entity Ownership Exception
          Modify Attribute Exception
// 開始年月
STARTYM = format(StartDate,"yyyy/MM")
attr = "ASSIG_GEN.START_YM:REFERENCE"
Set value for attr in TemporaryRegularAssigPO to STARTYM
          Invalid Attribute
          Attribute Not Found
          Invalid Property
          Invalid Occurrence
          Entity Ownership Exception
          Modify Attribute Exception
// 終了年月
ENDYM = format(EndDate,"yyyy/MM")
attr = "ASSIG_GEN.END_YM:REFERENCE"
Set value for attr in TemporaryRegularAssigPO to ENDYM
          Invalid Attribute
          Attribute Not Found
          Invalid Property
          Invalid Occurrence
          Entity Ownership Exception
          Modify Attribute Exception
// 
// 
// 
// 開始年月と終了年月から就業年月を算出する
yearStart = substring(STARTYM,0,4)
Output NUMERIC yearStart to Log LOG
yearEnd = substring(ENDYM,0,4)
Output NUMERIC yearEnd to Log LOG
monthStart = substring(STARTYM,5,7)
Output NUMERIC monthStart to Log LOG
monthEnd = substring(ENDYM,5,7)
Output NUMERIC monthEnd to Log LOG
// 
IF (monthStart > monthEnd)
          // 終了月よりも開始月が大きい場合、繰り下げ処理
          monthEnd = monthEnd + 12
          yearEnd = yearEnd - 1
          // 
// 
empYear = yearEnd - yearStart
empMonth = monthEnd - monthStart
empYm = empYear + "年" + empMonth + "ヶ月" 
// 
Output ALPHA empYm to Log Log
attr = "ASSIG_GEN.EMP_YM:REFERENCE"
Set value for attr in TemporaryRegularAssigPO to empYm
          Invalid Attribute
          Attribute Not Found
          Invalid Property
          Invalid Occurrence
          Entity Ownership Exception
          Modify Attribute Exception
// 
// 
// 
// 会社名
path = "HEADOFFICE" + RegSeg
HEADOFFICEID = _VIEW(path,"ID")
IF (HEADOFFICEID != 0 && HEADOFFICEID != _EMPTY)
          Get Entity HEADOFFICEID as HEADOFFICEPO
              Entity Not Found
          HEADOFFICENAME = HEADOFFICEPO.attribute("LE_GEN.NAME:REFERENCE")
          INDUSTRY = HEADOFFICEPO.attribute("IND_SECT(1).INDUSTRY:REFERENCE")
attr = "ASSIG_GEN.PRV_CO:REFERENCE"
Set value for attr in TemporaryRegularAssigPO to HEADOFFICENAME
          Invalid Attribute
          Attribute Not Found
          Invalid Property
          Invalid Occurrence
          Entity Ownership Exception
          Modify Attribute Exception
attr = "ASSIG_GEN.INDUSTRY:REFERENCE"
Set value for attr in TemporaryRegularAssigPO to INDUSTRY
          Invalid Attribute
          Attribute Not Found
          Invalid Property
          Invalid Occurrence
          Entity Ownership Exception
          Modify Attribute Exception
EMP_STATUS = _CODEID("RS Employment status\05")
attr = "ASSIG_GEN.EMp_STATUS:REFERENCE"
Set value for attr in TemporaryRegularAssigPO to EMP_STATUS
          Invalid Attribute
          Attribute Not Found
          Invalid Property
          Invalid Occurrence
          Entity Ownership Exception
          Modify Attribute Exception
// RS　要件に伴い、職歴項目追加設定2015/01/31************************************
// 
// START === Modified by Alexander Bikov, 28 Jul 2011. Eventum #9706 [Standard System- AWR]
LOOP ( Initialise rIndex = 1; Do While rIndex <=15 ; Increment rIndex By 1 )
          Output NUMERIC rIndex to Log LOG
          // ===============Rate Description=========
          path = "RATEDESCRIPTION" + rIndex + RegSeg
          RateDescription_ID = _VIEW(path,"ID")
          Output NUMERIC RateDescription_ID to Log LOG
          attribute = "ASSIG_RATE(Rate" + rIndex + ").RATE_CODE:REFERENCE"
          Set value for attribute in TemporaryRegularAssigPO to RateDescription_ID
              Invalid Attribute
              Attribute Not Found
              Invalid Property
              Invalid Occurrence
              Entity Ownership Exception
              Modify Attribute Exception
          // ===============Pre Pay==============
          path_PrePay = "PAY" + rIndex + RegSeg
          PrePay = _VIEW(path_PrePay)
          Output NUMERIC PrePay to Log LOG
          attribute = "ASSIG_RATE(Rate" + rIndex + ").PAY_RATE:REFERENCE"
          Set value for attribute in TemporaryRegularAssigPO to PrePay
              Invalid Attribute
              Attribute Not Found
              Invalid Property
              Invalid Occurrence
              Entity Ownership Exception
              Modify Attribute Exception
          // ===============Pre Charge============
          path_PreCharge = "CHARGE" + rIndex + RegSeg
          PreCharge = _VIEW(path_PreCharge)
          Output NUMERIC PreCharge to Log LOG
          attribute = "ASSIG_RATE(Rate" + rIndex + ").CHRG_RATE:REFERENCE"
          Set value for attribute in TemporaryRegularAssigPO to PreCharge
              Invalid Attribute
              Attribute Not Found
              Invalid Property
              Invalid Occurrence
              Entity Ownership Exception
              Modify Attribute Exception
          // Start===== Modified by Keisuke Yamamoto on 10.11.14; [RandstadJapanDev] Eventum ID 40312 ===== 
          // ===============Pre O/T============
          IF (rIndex > 1)
              path_PreExtra = "EXTRA" + rIndex + RegSeg
              PreExtra = _VIEW(path_PreExtra)
              Output NUMERIC PreExtra to Log Log
              Attribute = "ASSIG_RATE(Rate" + rIndex + ").ExTRA_RATIO:REFERENCE"
              Set value for Attribute in TemporaryRegularAssigPO to PreExtra
                  Invalid Attribute
                  Attribute Not Found
                  Invalid Property
                  Invalid Occurrence
                  Entity Ownership Exception
                  Modify Attribute Exception
              // ===============Pre O/T============
              path_PreBExtra = "BEXTRA" + rIndex + RegSeg
              PreBExtra = _VIEW(path_PreBExtra)
              Output NUMERIC PreBExtra to Log Log
              Attribute = "ASSIG_RATE(Rate" + rIndex + ").ExTRA_RATIOB:REFERENCE"
              Set value for Attribute in TemporaryRegularAssigPO to PreBExtra
                  Invalid Attribute
                  Attribute Not Found
                  Invalid Property
                  Invalid Occurrence
                  Entity Ownership Exception
                  Modify Attribute Exception
// 
msg = "create shifts"
Output Message msg to Log LOG
attribute = "VARIABLES.NIPRSI"
Execute GetDomainSetting attribute, NIPerc
Output NUMERIC NIPerc to Log LOG
attribute = "VARIABLES.HOL_PAY_PERC"
Execute GetDomainSetting attribute, HolidayPerc
Output NUMERIC HolidayPerc to Log LOG
// 
// prepare data for appointment
JobAdaptID = JobPO.attribute("JOB_GEN.JOB_ID:REFERENCE")
Output NUMERIC JobAdaptID to Log LOG
JobTitle = JobPO.attribute("JOB_GEN.JOB_TITLE:REFERENCE")
Output NUMERIC ClientID to Log LOG
Get Entity ClientID as ClientPO
          Entity Not Found
ClientAdaptID = ClientPO.attribute("CLIENT_GEN.CLIENT_ID:REFERENCE")
Output NUMERIC ClientAdaptID to Log LOG
Get Entity ContactID as ContactEntityPO
          Entity Not Found
ContactAdaptID = ContactEntityPO.attribute("PERSON_GEN.PERSON_ID:REFERENCE")
Output NUMERIC ContactAdaptID to Log LOG
messCodeDescription = "$JOBTitle="+JobTitle+"||$JOB="+JobAdaptID+"||$CLIENT="+ClientAdaptID+"||$CONTACT="+ContactAdaptID
Execute FormatMsg "TREGBOOK",messCodeDescription,AppNotes
Output ALPHA AppNotes to Log LOG
messCodeDescription = "$JobTitle="+JobTitle
Execute FormatMsg "BOOK",messCodeDescription,AppSubject
Output ALPHA AppSubject to Log LOG
BookingCode = "BOOK"
// 
SHIFT_COUNT = 0
RateCode = _CODEID("Rate Code\RATE01")
AssigForecastGM = 0
AssigForecastRevenue = 0
WorkDate = StartDate
// Start modify by Sergey D. eventum 39340 - add new fields
path = "Mon" + RegSeg
Mon = _VIEW(path)
path = "Tue" + RegSeg
Tue = _VIEW(path)
path = "Wed" + RegSeg
Wed = _VIEW(path)
path = "Thu" + RegSeg
Thu = _VIEW(path)
path = "Fri" + RegSeg
Fri = _VIEW(path)
path = "Sat" + RegSeg
Sat = _VIEW(path)
path = "Sun" + RegSeg
Sun = _VIEW(path)
// End modify by Sergey D. eventum 39340 - add new fields
WHILE( WorkDate <= EndDate)
          Output DATE WorkDate to Log LOG
          Execute GetDayOfWeek WorkDate,DayOfWeek
          Output NUMERIC DayOfWeek to Log LOG
          Output ALPHA Mon to Log LOG
          Output ALPHA Tue to Log LOG
          Output ALPHA Wed to Log LOG
          Output ALPHA Thu to Log LOG
          Output ALPHA Fri to Log LOG
          Output ALPHA Sat to Log LOG
          Output ALPHA Sun to Log LOG
          IF ((DayOfWeek==1 && Mon == "Y") || (DayOfWeek==2 && Tue == "Y") || (DayOfWeek==3 && Wed == "Y") || (DayOfWeek==4 && Thu == "Y") || (DayOfWeek==5 && Fri == "Y") || (DayOfWeek==6 && Sat == "Y") || (DayOfWeek==7 && Sun == "Y"))
              Execute CreateShift ShiftEntityID,JobID,WorkDate,StartTime,EndTime,Overnight,PaidHours,RateCode,TempRegularAssignmentID,CandidateID,candPayType,Charge1,Pay1,NIPerc,HolidayPerc,ForecastGM,ForecastRevenue, BreakStart1, BreakEnd1, BreakStart2, BreakEnd2, BreakStart3, BreakEnd3, BreakStart4, BreakEnd4, BreakStart5, BreakEnd5
          ELSE
              WorkDate = WorkDate + _DAYS(1)
              Output DATE WorkDate to Log LOG
              CONTINUE
          // START====Modified by Tatiana Semenishina 26/12/12 - Eventum#20091 - [Standart system] Revenue - 11.2 Fill Temp Regular Job
          Output NUMERIC ShiftEntityID to Log LOG
          SHIFT_COUNT = SHIFT_COUNT + 1
          Output NUMERIC ForecastGM to Log LOG
          Output NUMERIC ForecastRevenue to Log LOG
          AssigForecastGM = AssigForecastGM + ForecastGM
          Output NUMERIC AssigForecastGM to Log LOG
          AssigForecastRevenue = AssigForecastRevenue + ForecastRevenue
          Output NUMERIC AssigForecastRevenue to Log LOG
          // END====Modified by Tatiana Semenishina 26/12/12 - Eventum#20091 - [Standart system] Revenue - 11.2 Fill Temp Regular Job
          // 
          // ********************
          IF (Overnight=="Y")
              NextDate = WorkDate+_DAYS(1)
          IF (Overnight==_EMPTY || Overnight=="N")
              NextDate = WorkDate
          Execute UpdateBookingCalender WorkDate, NextDate, StartTime, EndTime, CandidateID, AppSubject, AppNotes, BookingCode
          // 
          WorkDate = WorkDate + _DAYS(1)
// 
Output NUMERIC SHIFT_COUNT to Log LOG
msg = "assignment revenue and gross margin"
Output Message msg to Log LOG
Output NUMERIC AssigForecastGM to Log LOG
Output NUMERIC AssigForecastRevenue to Log LOG
// START====Modified by Tatiana Semenishina 26/12/12 - Eventum#20091 - [Standart system] Revenue - 11.2 Fill Temp Regular Job
Set value for "ASSIG_GEN.FORE_GM:REFERENCE" in TemporaryRegularAssigPO to AssigForecastGM
          Invalid Attribute
          Attribute Not Found
          Invalid Property
          Invalid Occurrence
          Entity Ownership Exception
          Modify Attribute Exception
Set value for "ASSIG_GEN.FORE_REV:REFERENCE" in TemporaryRegularAssigPO to AssigForecastRevenue
          Invalid Attribute
          Attribute Not Found
          Invalid Property
          Invalid Occurrence
          Entity Ownership Exception
          Modify Attribute Exception
// START===Modified by Igor Udovychenko on 19/06/2013 for Eventum #24854 [Standard System]
Execute AssignmentDates TempRegularAssignmentID, assigStartDT, assigEndDT
Output DATE assigStartDT to Log LOG
Output DATE assigEndDT to Log LOG
Set value for "ASSIG_GEN.START_DT:REFERENCE" in TemporaryRegularAssigPO to assigStartDT
          Invalid Attribute
          Attribute Not Found
          Invalid Property
          Invalid Occurrence
          Entity Ownership Exception
          Modify Attribute Exception
Set value for "ASSIG_GEN.END_DT:REFERENCE" in TemporaryRegularAssigPO to assigEndDT
          Invalid Attribute
          Attribute Not Found
          Invalid Property
          Invalid Occurrence
          Entity Ownership Exception
          Modify Attribute Exception
// END===Modified by Igor Udovychenko on 19/06/2013 for Eventum #24854 [Standard System]
Save Entity TemporaryRegularAssigPO
          Entity Not Found
          Entity Not Locked
          Entity Ownership Exception
// END====Modified by Tatiana Semenishina 26/12/12 - Eventum#20091 - [Standart system] Revenue - 11.2 Fill Temp Regular Job
// 
// 
// ==================Update Job Entity====================
msg = "update job"
Output Message msg to Log LOG
Time = _TIME
today = _TODAY
SoFar = JobPO.attribute("TJOB_GEN.NO_SOFAR:REFERENCE")
required = JobPO.attribute("TJOB_GEN.NO_REQ:REFERENCE")
SoFar = SoFar+1
IF (SoFar >= required)
          FillTempJob = _CODEID("Job Status\T")
          Set value for "JOB_GEN.STATUS:REFERENCE" in JobPO to FillTempJob
              Invalid Attribute
              Attribute Not Found
              Invalid Property
              Invalid Occurrence
              Entity Ownership Exception
              Modify Attribute Exception
NoSoFar = "TJOB_GEN.NO_SOFAR:REFERENCE"
Set value for "TJOB_GEN.NO_SOFAR:REFERENCE" in JobPO to SoFar
          Invalid Attribute
          Attribute Not Found
          Invalid Property
          Invalid Occurrence
          Entity Ownership Exception
          Modify Attribute Exception
Set value for "JOB_HIST.FILLED_DT:REFERENCE" in JobPO to today
          Invalid Attribute
          Attribute Not Found
          Invalid Property
          Invalid Occurrence
          Entity Ownership Exception
          Modify Attribute Exception
Set value for "JOB_HIST.FILLED_TM:REFERENCE" in JobPO to Time
          Invalid Attribute
          Attribute Not Found
          Invalid Property
          Invalid Occurrence
          Entity Ownership Exception
          Modify Attribute Exception
// ?????
Save Entity JobPO
          Entity Not Found
          Entity Not Locked
          Entity Ownership Exception
// 
// 
// ===Start= Modified by Nadezhda Sveredjuk on 8.05.2012; eventum 15715; Standard System ===
msg = "update candidate"
Output Message msg to Log LOG
// update candidate status
Edit Entity CandidateID using UpdateCandidatePO to refer to it
          Entity Not Found
          Entity Already Locked
          Entity Ownership Exception
CandidateStatus = _codeID("Candidate Status\P")
Set value for "CAND_GEN.STATUS:REFERENCE" in UpdateCandidatePO to CandidateStatus
          Invalid Attribute
          Attribute Not Found
          Invalid Property
          Invalid Occurrence
          Entity Ownership Exception
          Modify Attribute Exception
Save Entity UpdateCandidatePO
          Entity Not Found
          Entity Not Locked
          Entity Ownership Exception
// ===End= Modified by Nadezhda Sveredjuk on 8.05.2012; eventum 15715; Standard System ===
// 
// ==========Update ShortList=============
msg = "update shortlist"
Output Message msg to Log LOG
Begin fetch PropertyPO for property "X_SHORT_CAND"
          Invalid Property
Add condition to fetch PropertyPO where attribute "JOB" is equal to JobID
          Invalid Attribute
Add condition to fetch PropertyPO where attribute "CANDIDATE" is equal to CandidateID
          Invalid Attribute
Execute fetch PropertyPO
ShortlistID = PropertyPO.attribute(1,"SHORTLIST")
Output NUMERIC ShortlistID to Log LOG
IF (ShortlistID > 0)
          Edit Entity ShortlistID using ShortlistPO to refer to it
              Entity Not Found
              Entity Already Locked
              Entity Ownership Exception
          AssignedStatus = _CODEID("Shortlist Status\AS")
          Set value for "SHORT_GEN.STATUS:REFERENCE" in ShortlistPO to AssignedStatus
              Invalid Attribute
              Attribute Not Found
              Invalid Property
              Invalid Occurrence
              Entity Ownership Exception
              Modify Attribute Exception
          Set value for "SHORT_GEN.ASSIG_DATE:REFERENCE" in ShortlistPO to today
              Invalid Attribute
              Attribute Not Found
              Invalid Property
              Invalid Occurrence
              Entity Ownership Exception
              Modify Attribute Exception
          // START=== Modified by Alexander Bikov, 23/08/2013. Eventum # 27042 ==== Set 'N' to SHORT_GEN.A_CENTRAL if shortlist created not from Adapt Central
          ACentralYN = ShortlistPO.attribute("SHORT_GEN.A_CENTRAL:REFERENCE")
          Output ALPHA ACentralYN to Log LOG
          IF (ACentralYN != "Y")
              Set value for "SHORT_GEN.A_CENTRAL:REFERENCE" in ShortlistPO to "N"
                  Invalid Attribute
                  Attribute Not Found
                  Invalid Property
                  Invalid Occurrence
                  Entity Ownership Exception
                  Modify Attribute Exception
          // END === Modified by Alexander Bikov, 23/08/2013. Eventum # 27042 ====
          Save Entity ShortlistPO
              Entity Not Found
              Entity Not Locked
              Entity Ownership Exception
// 
// 
msg = "AWR checks, notifications and clock creation"
Output Message msg to Log LOG
IF (SameClientYN != _EMPTY)
          Execute RequestPlacementHistory_Worker_AW CandidateID, ClientID, Notes1
Execute UpdateAssignemt_StatusAndAWR TempRegularAssignmentID, CandidateID, HirerID,0,Employee
msg = "check in task, activity"
Output Message msg to Log LOG
// If Assignment has checked, do not create task for Check In
IF (StartDate >= today)
          Execute TemporaryRegularBookingTask TempRegularAssignmentID
Execute ActivityShortlist CandidateID, JobID, ClientID, ContactID 
Execute FillJobSetContact JobID, ContactID

path = "ASSIGNMENTID" + RegSeg
Output NUMERIC TempRegularAssignmentID to Log LOG
_VIEW(path) = TempRegularAssignmentID

msg = "Executing BO: JournalEntryforNodocs"
Output Message msg to Log LOG
Execute JournalEntryforNoDocs CandidateID,ClientID,JobID,ContactID,Notes1

Execute SetContractCode TempRegularAssignmentID
Execute GenerateAssignmentContract TempRegularAssignmentID

docCatName = "Contract Documentation"
Open Document Library for Entity jobID as docLibPO
          Invalid ID
Goto Category docCatName in docLibPO
          Invalid Category
docCount = docLibPO.documentCount()
Output NUMERIC docCount to Log LOG
IF (docCount <= 0)
          Execute GenerateJobContract JobID
// 
// 
// =================DATA FOR DOCUMENTS CREATION=====================
msg = "documents"
Output Message msg to Log LOG
// 
Get User by ID UserID as UserPO
          Invalid ID
UserName = UserPO.attribute("Full Name")
UserJobTitle = UserPO.attribute("JOB TITLE")
Output NUMERIC TempRegularAssignmentID to Log LOG
Post = _CODEID("Send By\P")
Execute GetUserEmailOfficePhone 
DDI = _PARAM(4,"GetUserEmailOfficePhone")
Output ALPHA DDI to Log LOG
ConsEmail = _PARAM(3,"GetUserEmailOfficePhone")
Output ALPHA ConsEmail to Log LOG
// START===Modified by Igor Udovychenko on 19/06/2013 for Eventum #24854 [Standard System]
/* IF (1 == 2) */
          /* assigStartDT = TemporaryRegularAssigPO.attribute("ASSIG_GEN.START_DT:REFERENCE") */
// END===Modified by Igor Udovychenko on 19/06/2013 for Eventum #24854 [Standard System]
assignPayRate1 = TemporaryRegularAssigPO.attribute("ASSIG_RATE(Rate1).PAY_RATE:REFERENCE")
assignPayRate1_str = format(assignPayRate1,"##0.00")
Output ALPHA assignPayRate1_str to Log LOG
Output NUMERIC assignPayRate1 to Log LOG
assigPayRate6 = TemporaryRegularAssigPO.attribute("ASSIG_RATE(Rate6).PAY_RATE:REFERENCE")
assigPayRate6_str = format(assigPayRate6,"##0.00")
assigPayRate7 = TemporaryRegularAssigPO.attribute("ASSIG_RATE(Rate7).PAY_RATE:REFERENCE")
assigPayRate7_str = format(assigPayRate7,"##0.00")
assigChargeRate = TemporaryRegularAssigPO.attribute("ASSIG_RATE(Rate1).CHRG_RATE:REFERENCE")
assigChargeRate_str = format(assigChargeRate,"##0.00")
// 

DocumentCategory = "Temporary Correspondence\Candidate Letters"
DocumentCategory1 = "Temporary Correspondence\Contact Letters"
// ========= Delete all the Documents from the Doc Category ======
Open Document Library for User UserID as DocLib
          Invalid ID
Goto Category DocumentCategory in DocLib
          Invalid Category
Execute ClearUserDocCat UserID, DocumentCategory
Goto Category DocumentCategory1 in DocLib
          Invalid Category
Execute ClearUserDocCat UserID, DocumentCategory1
// 
DocumentCategory = "Temporary Correspondence\Candidate Emails"
DocumentCategory1 = "Temporary Correspondence\Contact Emails"
Open Document Library for User UserID as DocLib
          Invalid ID
Goto Category DocumentCategory in DocLib
          Invalid Category
Execute ClearUserDocCat UserID, DocumentCategory
Goto Category DocumentCategory1 in DocLib
          Invalid Category
Execute ClearUserDocCat UserID, DocumentCategory1
// 
Output NUMERIC CandidateID to Log LOG
CandidateName = CandidatePO.attribute("PERSON_GEN.FULLNAME:REFERENCE")
CandidateSalutation = CandidatePO.attribute("PERSON_GEN.SALUTATION:REFERENCE")
CandidateTitle = CandidatePO.attribute("PERSON_GEN.TITLE:REFERENCE")
CandidateTitleDesc = format(CandidateTitle,"DESCRIPTION") 
CandStreet1 = CandidatePO.attribute("ADDRESS(Primary).STREET1:REFERENCE")
CandStreet2 = CandidatePO.attribute("ADDRESS(Primary).STREET2:REFERENCE")
CandLocality = CandidatePO.attribute("ADDRESS(Primary).LOCALITY:REFERENCE")
CandTown = CandidatePO.attribute("ADDRESS(Primary).TOWN:REFERENCE")
CandCounty = CandidatePO.attribute("ADDRESS(Primary).COUNTY:REFERENCE")
CandidateCounty = CandidatePO.attribute("ADDRESS(Primary).COUNTRY:REFERENCE")
CandCountry = format(CandidateCounty,"DESCRIPTION")
CandPostCode = CandidatePO.attribute("ADDRESS(Primary).POST_CODE:REFERENCE")
// 
Output NUMERIC ClientID to Log LOG
ClientName = ClientPO.attribute("CLIENT_GEN.NAME:REFERENCE")
ClientStreet1 = ClientPO.attribute("ADDRESS(Primary).STREET1:REFERENCE")
ClientStreet2 = ClientPO.attribute("ADDRESS(Primary).STREET2:REFERENCE")
ClientLocality = ClientPO.attribute("ADDRESS(Primary).LOCALITY:REFERENCE")
ClientTown = ClientPO.attribute("ADDRESS(Primary).TOWN:REFERENCE")
ClientCounty = ClientPO.attribute("ADDRESS(Primary).COUNTY:REFERENCE")
clientCountry = ClientPO.attribute("ADDRESS(Primary).COUNTRY:REFERENCE")
cCountry = format(clientCountry,"DESCRIPTION")
ClientPostCode = ClientPO.attribute("ADDRESS(Primary).POST_CODE:REFERENCE")
// 
ContactName = ContactEntityPO.attribute("PERSON_GEN.FULLNAME:REFERENCE")
ContactSalutation = ContactEntityPO.attribute("PERSON_GEN.SALUTATION:REFERENCE")
ContactTitle1 = ContactEntityPO.attribute("PERSON_GEN.TITLE:REFERENCE")
ContactTitle = format(ContactTitle1,"DESCRIPTION")
ContactStreet1 = ContactEntityPO.attribute("ADDRESS(Primary).STREET1:REFERENCE")
ContactjobTitle = ContactEntityPO.attribute("CONT_GEN.JOB_TITLE:REFERENCE")
IF (ContactStreet1==_EMPTY)
          ContactStreet1 = ClientPO.attribute("ADDRESS(Primary).STREET1:REFERENCE")
          ContactStreet2 = ClientPO.attribute("ADDRESS(Primary).STREET2:REFERENCE")
          ContactLocality = ClientPO.attribute("ADDRESS(Primary).LOCALITY:REFERENCE")
          ContactTown = ClientPO.attribute("ADDRESS(Primary).TOWN:REFERENCE")
          ContactCounty = ClientPO.attribute("ADDRESS(Primary).COUNTY:REFERENCE")
          ContactPostCode = ClientPO.attribute("ADDRESS(Primary).POST_CODE:REFERENCE")
          ContactCountry1 = ClientPO.attribute("ADDRESS(Primary).COUNTRY:REFERENCE")
ELSE
          ContactStreet1 = ContactEntityPO.attribute("ADDRESS(Primary).STREET1:REFERENCE")
          ContactStreet2 = ContactEntityPO.attribute("ADDRESS(Primary).STREET2:REFERENCE")
          ContactLocality = ContactEntityPO.attribute("ADDRESS(Primary).LOCALITY:REFERENCE")
          ContactTown = ContactEntityPO.attribute("ADDRESS(Primary).TOWN:REFERENCE")
          ContactCounty = ContactEntityPO.attribute("ADDRESS(Primary).COUNTY:REFERENCE")
          ContactPostCode = ContactEntityPO.attribute("ADDRESS(Primary).POST_CODE:REFERENCE")
          ContactCountry1 = ContactEntityPO.attribute("ADDRESS(Primary).COUNTRY:REFERENCE")
ContactCountry = format(ContactCountry1,"DESCRIPTION")
// 
// START === Added by Alexander Bikov, Eventum # 18948 === 
Execute Cube19_Generic_EntityBO_CN TempRegularAssignmentID, AssignmentRole
// END === Added by Alexander Bikov, Eventum # 18948 === 
// START === Modified by Alexander Bikov, 27/04/2013. Cube 19 integration =============
IF (CandidateStatusOriginal != CandidateStatus)
          Execute Cube19_Generic_EntityBO_CN CandidateID, CandidateRole
// END === Modified by Alexander Bikov, 27/04/2013. Cube 19 integration =============
// 
// 
IF (SendDocsBy == Post)
          DocumentCategory = "Temporary Correspondence\Candidate Letters"

          docCatName = "Contract Documentation"
          Open Document Library for Entity TempRegularAssignmentID as AssignmentDocLibPO
              Invalid ID
          Goto Category docCatName in AssignmentDocLibPO
              Invalid Category
          docCount = AssignmentDocLibPO.documentCount()
          Output NUMERIC docCount to Log LOG
          Get Default Document from AssignmentDocLibPO as DocName
          // End ===== Modified by Anton M on 06.11.14; [RandstadJapanDev] Eventum ID 41112 ===== 
          // 
          Open Document Library for User UserID as DocLibrary
              Invalid ID
          Goto Category DocumentCategory in DocLibrary
              Invalid Category
          Add Document DocName to DocLibrary
              Invalid Object
          // ======= Create document for Contact =======
          Template1 = "Temp Regular Confirmation Contact"
          Output ALPHA Template1 to Log LOG
          DocumentCategory1 = "Temporary Correspondence\Contact Letters"

          docCatName = "Contract Documentation"
          Open Document Library for Entity JobID as JobDocLibPO
              Invalid ID
          Goto Category docCatName in JobDocLibPO
              Invalid Category
          docCount = JobDocLibPO.documentCount()
          Output NUMERIC docCount to Log Log
          Get Default Document from JobDocLibPO as DocName
          // End ===== Modified by Anton M on 06.11.14; [RandstadJapanDev] Eventum ID 41112 ===== 
          // 
          Open Document Library for User UserID as DocLibrary
              Invalid ID
          Goto Category DocumentCategory1 in DocLibrary
              Invalid Category
          Add Document DocName to DocLibrary
              Invalid Object
          // 
          Close Log LOG
          Execute W_TempRegularBookingPost and close the current view.
          // ======= End of Create document for Contact =======
Email = _CODEID("Send By\E")
IF (SendDocsBy == Email)
          // *************************Create Document for Candidate***************************
          Template = "Temp Regular Confirmation Candidate Email"
          Execute SendEmailMultiLanguage CandidateID,Language
          Create new merge dataset as mergeDataPO
          Set mergefield "CandidateSalutation" in mergeDataPO to CandidateSalutation
          Set mergefield "ClientName" in mergeDataPO to ClientName
          Set mergefield "AssignmentStartDate" in mergeDataPO to assigStartDT
          Set mergefield "AssignmentRate" in mergeDataPO to assignPayRate1
          Set mergefield "Currency" in mergeDataPO to currency_Alpha
          Set mergefield "OTRate1" in mergeDataPO to assigPayRate6
          Set mergefield "OTRate2" in mergeDataPO to assigPayRate7
          Set mergefield "ConsultantName" in mergeDataPO to UserName
          Set mergefield "ConsultantJobTitle" in mergeDataPO to UserJobTitle
          Set mergefield "ConsEmail" in mergeDataPO to ConsEmail
          Set mergefield "DDI" in mergeDataPO to DDI
          Create HTML Document DocName from template Template in language Language using mergeset mergeDataPO
              Unknown Template
          Open Document Library for User UserID as DocLibrary
              Invalid ID
          DocumentCategory = "Temporary Correspondence\Candidate Emails"
          Goto Category DocumentCategory in DocLibrary
              Invalid Category
          Add Document DocName to DocLibrary
              Invalid Object
          // ******************Create Document for Contact**************************
          DocumentCategory = "Temporary Correspondence\Contact Emails"
          Execute SendEmailMultiLanguage ContactID,Language
          // ======= Create document for Contact Email=======
          Template1 = "Temp Regular Confirmation Contact Email"
          Create new merge dataset as mergeDataPO
          Set mergefield "ContactSalutation" in mergeDataPO to ContactSalutation
          Set mergefield "ClientName" in mergeDataPO to ClientName
          Set mergefield "CandidateName" in mergeDataPO to CandidateName
          Set mergefield "AssignmentStartDate" in mergeDataPO to assigStartDT
          Set mergefield "CandidateSalutation" in mergeDataPO to CandidateSalutation
          Set mergefield "CRate" in mergeDataPO to assigChargeRate

          Set mergefield "JobTitle" in mergeDataPO to JobTitle
          Set mergefield "ConsultantName" in mergeDataPO to UserName
          Set mergefield "JobTitle" in mergeDataPO to JobTitle
          Set mergefield "ConsultantJobTitle" in mergeDataPO to UserJobTitle
          Set mergefield "Currency" in mergeDataPO to currency_Alpha
          Set mergefield "ConsEmail" in mergeDataPO to ConsEmail
          Set mergefield "DDI" in mergeDataPO to DDI
          Create HTML Document DocName from template Template1 in language Language using mergeset mergeDataPO
              Unknown Template
          Open Document Library for User UserID as DocLibrary
              Invalid ID
          DocumentCategory1 = "Temporary Correspondence\Contact Emails"
          Goto Category DocumentCategory1 in DocLibrary
              Invalid Category
          Add Document DocName to DocLibrary
              Invalid Object
          // 
          // =start==Modified by Oksana D, 13/06/12, Eventum #16549, Standard System
          DocumentCategory = "Temporary Correspondence\ContactEmailAttachments"
          Execute ClearUserDocCat UserID,DocumentCategory
          DocumentCategory = "Temporary Correspondence\CandidateEmailAttachments"
          Execute ClearUserDocCat UserID,DocumentCategory
          // 
          IF (EBC == "Y")
              DocumentCategory = "Temporary Correspondence\CandidateEmailAttachments"

              docCatName = "Contract Documentation"
              Open Document Library for Entity TempRegularAssignmentID as AssignmentDocLibPO
                  Invalid ID
              Goto Category docCatName in AssignmentDocLibPO
                  Invalid Category
              docCount = AssignmentDocLibPO.documentCount()
              Output NUMERIC docCount to Log LOG
              Get Default Document from AssignmentDocLibPO as DocName
              // End ===== Modified by Anton M on 06.11.14; [RandstadJapanDev] Eventum ID 41112 ===== 
              // 
              Open Document Library for User UserID as DocLibrary
                  Invalid ID
              Goto Category DocumentCategory in DocLibrary
                  Invalid Category
              Add Document DocName to DocLibrary
                  Invalid Object
              // 
              // ======= Create document for Contact =======
              // 
              Template1 = "Temp Regular Confirmation Contact"
              Output ALPHA Template1 to Log LOG
              DocumentCategory1 = "Temporary Correspondence\ContactEmailAttachments"

              docCatName = "Contract Documentation"
              Open Document Library for Entity JobID as JobDocLibPO
                  Invalid ID
              Goto Category docCatName in JobDocLibPO
                  Invalid Category
              docCount = JobDocLibPO.documentCount()
              Output NUMERIC docCount to Log Log
              Get Default Document from JobDocLibPO as DocName
              // End ===== Modified by Anton M on 06.11.14; [RandstadJapanDev] Eventum ID 41112 ===== 
              // 
              Open Document Library for User UserID as DocLibrary
                  Invalid ID
              Goto Category DocumentCategory1 in DocLibrary
                  Invalid Category
              Add Document DocName to DocLibrary
                  Invalid Object
          // =end==Modified by Oksana D, 13/06/12, Eventum #16549, Standard System
          // 
          Close Log LOG
          Execute W_TempRegularBookingEmail and close the current view.
SendDocsBy1 = _CODEID("Send By\NO")
IF (SendDocsBy == SendDocsBy1)
          Close Log LOG
          Execute TempRegularAssignmentID and close the current view.
