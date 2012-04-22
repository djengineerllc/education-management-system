<div style="height:6px;"></div>
<div style="text-align:center">证 明</div>
<div style="height:12px;"></div>
<div style="text-indent: 2em">
${stuInfo.userName}，<@code codeType='Sex' data=stuInfo.sex />，${stuInfo.birthDate?string("yyyy年MM月dd日")}出生。${stuInfo.admissionTime?string("yyyy年MM月")}入学，学号${stuInfo.stuNo}，于2009年9月至2011年7月就读我院预本硕项目。
</div>
<div style="text-indent: 2em">
特此证明。
</div>
<div style="height:8px;"></div>
<div style="text-align:right">厦门大学国际学院</div>
<div style="text-align:right">${sysDateZH}</div>

<div style="height:60px;"></div>

<div style="text-align:center">CERTIFICATION</div>
<div style="height:12px;"></div>
<div style="text-indent: 2em">
This is to certify that ${stuInfo.pinyin}, <@code mode='getNameEnByValue' codeType='Sex' data=stuInfo.sex />, who was born on June 15th, 1990, has been enrolled by our college in September, 2009, with Student ID. 27620095575115. He was studying under the HND program from September 2009 to July 2011. 
</div>
<div style="height:8px;"></div>
<div style="text-align:right">The International College of Xiamen University</div>
<div style="text-align:right">Date: ${sysDateEN}</div>