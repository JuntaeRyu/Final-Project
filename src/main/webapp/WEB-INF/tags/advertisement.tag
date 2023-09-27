<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div id="advertisement">
	<c:forEach var="adata" items="${adatas}">
		<div id="product">
			<b id="productName">${adata.item}</b> <a href="${adata.siteUrl}"
				class="productUrl"> <img alt="${adata.item}"
				src="${adata.itemImg}" id="productImg">
			</a>
			<div id="productPay">${adata.itemPay}</div>
		</div>
	</c:forEach>
</div>
<script>
    window.addEventListener("scroll", function() {
        var advertisement = document.getElementById("advertisement");
        var scrollPosition = window.scrollY || document.documentElement.scrollTop;

        // 원하는 스크롤 위치 (예: 200px)에서 광고를 보이게 합니다.
        if (scrollPosition >= 900) {
            advertisement.style.display = "flex";
        } else {
            advertisement.style.display = "none";
        }
    });
</script>