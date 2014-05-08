<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@include file="htmlElements/top.jsp"%>

<div class="container">
	<div class="row">
		<div class="col-md-12">
			<h2>List Finder</h2>
			<p>
				Besoin d'aide pour choisir efficacement une structure de données ?<br />
				Suivez notre formulaire ci-dessous pour obtenir la structure la plus
				optimisée pour votre utilisation<br />
			</p>
			<form role="form">
				<div class="form-group">
					<label for="firstQuestion">${firstQuestionLabel}</label>
					<select class="form-control">
						<option></option>
						<c:forEach items="${firstQuestionAnswers}" var="answer">
							<option value="${answer.key}">${answer.value }</option>
						</c:forEach>
					</select>
				</div>
			</form>
		</div>
	</div>
</div>
<%@include file="htmlElements/bottom.jsp"%>