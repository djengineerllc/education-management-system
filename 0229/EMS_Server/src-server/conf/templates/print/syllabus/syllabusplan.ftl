<div style="height:6px;"></div>
<div style="text-align:center"><b>课程一览表(XX学期)</b></div>
<div style="height:12px;"></div>
<table border="1" cellpadding="0" cellspacing="0" width="100%">
	<thead>
		<tr>
			<th style="text-align:center;" rowspan="2">课时</th>
			<th style="text-align:center;" rowspan="2">班级</th>
			<th style="text-align:center;" rowspan="2">单双</th>
			<th style="text-align:center;" rowspan="1" colspan="3">周一</th>
			<th style="text-align:center;" rowspan="1" colspan="3">周二</th>
			<th style="text-align:center;" rowspan="1" colspan="3">周三</th>
			<th style="text-align:center;" rowspan="1" colspan="3">周四</th>
			<th style="text-align:center;" rowspan="1" colspan="3">周五</th>
		</tr>
		<tr>
			<th style="text-align:center;">课程</th>
			<th style="text-align:center;">教师</th>
			<th style="text-align:center;">教室</th>
			<th style="text-align:center;">课程</th>
			<th style="text-align:center;">教师</th>
			<th style="text-align:center;">教室</th>
			<th style="text-align:center;">课程</th>
			<th style="text-align:center;">教师</th>
			<th style="text-align:center;">教室</th>
			<th style="text-align:center;">课程</th>
			<th style="text-align:center;">教师</th>
			<th style="text-align:center;">教室</th>
			<th style="text-align:center;">课程</th>
			<th style="text-align:center;">教师</th>
			<th style="text-align:center;">教室</th>
		</tr>
	</thead>
	<tbody>
		<#list items as item>
		<tr>
			<#if (item_index % (classCount * 2)) == 0>
			<td rowSpan="${classCount * 2}">${item.lesson}</td>
			</#if>
			<#if (item_index % classCount) == 0>
			<td rowSpan="${classCount}">${item.classCode}</td>
			</#if>
			<td>${item.oeInd}</td>
			<td>${item.monCourse}</td>
			<td>${item.monTeacher}</td>
			<td>${item.monRoom}</td>
			<td>${item.tueCourse}</td>
			<td>${item.tueTeacher}</td>
			<td>${item.tueRoom}</td>
			<td>${item.webCourse}</td>
			<td>${item.webTeacher}</td>
			<td>${item.webRoom}</td>
			<td>${item.thuCourse}</td>
			<td>${item.thuTeacher}</td>
			<td>${item.thuRoom}</td>
			<td>${item.friCourse}</td>
			<td>${item.friTeacher}</td>
			<td>${item.friRoom}</td>
		</tr>
		</#list>
	</tbody>
</table>