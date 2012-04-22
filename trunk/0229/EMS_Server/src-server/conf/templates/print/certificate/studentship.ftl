<div style="height:6px;"></div>
<div style="text-align:center">在学证明</div>
<div style="height:12px;"></div>
<div style="text-indent: 2em">
${stuInfo.userName}，<@code codeType='Sex' data=stuInfo.sex />，${stuInfo.birthDate?string("yyyy年MM月dd日")}出生。${stuInfo.admissionTime?string("yyyy年MM月")}入学，学号${stuInfo.stuNo}，现就读于我院预本硕项目二年级。
</div>
<div style="text-indent: 2em">
特此证明。
</div>
<div style="text-align:right">厦门大学国际学院</div>
<div style="text-align:right">${sysDateZH}</div>

<div style="height:60px;"></div>

<div style="text-align:center">CERTIFICATION</div>
<div style="height:12px;"></div>
<div style="text-indent: 2em">
This is to certify that ${stuInfo.pinyin}, <@code mode='getNameEnByValue' codeType='Sex' data=stuInfo.sex />, who was born on October 11, 1991, has been enrolled by our college in September, 2010, with Student ID. 27620105575040. She is now studying under the HND program as a 2nd year student. 
</div>
<div style="height:8px;"></div>
<div style="text-align:right">The International College of Xiamen University</div>
<div style="text-align:right">Date: ${sysDateEN}</div>