<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:choose>
	<c:when test="${not empty nextQuestion }">
		<div class="panel panel-primary">
			<div class="panel-heading">
				${nextQuestion.questionValue}
			</div>
			<div class="panel-body">
				<select class="form-control" name="nextQuestion" id="nextQuestion${nextQuestion.id}" onchange="javascript:doAjaxRequestForNextQuestion('${nextQuestion.id}',this)">
					<option></option>
					<c:forEach items="${nextQuestion.listeReponse}" var="answer">
						<option value="${answer.key}">${answer.value }</option>
					</c:forEach>
				</select>
			</div>
		</div>
	</c:when>
	<c:when test="${not empty listeFinale }">
		<div class="panel panel-info">
			<div class="panel-heading">
				Vous devriez utiliser : 
			</div>
			<div class="panel-body">
				${listeFinale.name}
			</div>
		</div>
	</c:when>
	<c:otherwise>
		
	</c:otherwise>
</c:choose>
