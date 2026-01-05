<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
		<!DOCTYPE html>
		<html>
		<head>
			<meta charset="ISO-8859-1">
			<title>Insert title here</title>
			<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
				integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH"
				crossorigin="anonymous">
			<link rel="stylesheet" href="./assets/css/custom.css">
			</link>

			<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
		</head>

		<body>
			<section class="mt-3">
				<div class="cust-cont">
					<div class="row mb-5">
						<div class="col">
							<div>
								<h1>Shopping cart</h1>
								<div>
									<p class="lead text-muted">
										You have
										${rowCount}
										items in your cart.
									</p>
								</div>
							</div>
							<c:forEach items="${cartItems}" var="cart">
								<div class="item-of-cart text-start">
									<div class="row d-flex align-items-center text-start text-md-center">
										<div class="col-12 col-md-5 text-center product">
											<b>${cart.product.product}</b>
										</div>
										<div class="col-12 col-md-7 mt-4 mt-md-0 text-center">
											<div class="row align-items-center">
												<div class="col-md-4">
													<div class="row">
														<div class="col-6 col-md-12 text-end text-md-center quantity">
															<button class="btn btn-secondary dropdown-toggle"
																type="button" data-bs-toggle="dropdown"
																data-bs-auto-close="true" aria-expanded="false">
																${cart.quantity}</button>
															<ul class="dropdown-menu">
																<c:forEach begin="1" end="${cart.product.quantity}"
																	var="i">
																	<li>
																		<a class="dropdown-item"
																			onclick="updateDropdown(this)"
																			data-cart-id="${cart.id}"
																			data-quantity="${i}">
																			${i}
																		</a>
																	</li>
																</c:forEach>
															</ul>
														</div>
													</div>
												</div>
												<div class="col-md-3">
													<div class="row">
														<div class="col-6 d-md-none text-muted">Total
															price</div>
														<div class="col-6 col-md-12 text-end text-md-center">
															<b>$${cart.price}</b>
														</div>
													</div>
												</div>
												<div class="col-2 d-none d-md-block text-end">
													<a class="cart-remove text-muted text-end btn"
														onclick="handleRemoveCart(this)"> <svg
															xmlns="http://www.w3.org/2000/svg" width="20" height="20"
															fill="currentColor" class="bi bi-x-circle"
															viewBox="0 0 16 16">
															<path
																d="M8 15A7 7 0 1 1 8 1a7 7 0 0 1 0 14m0 1A8 8 0 1 0 8 0a8 8 0 0 0 0 16" />
															<path
																d="M4.646 4.646a.5.5 0 0 1 .708 0L8 7.293l2.646-2.647a.5.5 0 0 1 .708.708L8.707 8l2.647 2.646a.5.5 0 0 1-.708.708L8 8.707l-2.646 2.647a.5.5 0 0 1-.708-.708L7.293 8 4.646 5.354a.5.5 0 0 1 0-.708" />
														</svg>
													</a>
												</div>
											</div>
										</div>

									</div>
								</div>
								</c:forEach>
						</div>
						<div class="col">
							<div class="card mb-5">
								<div class="card-header">
									<h6 class="mb-0">Order Summary</h6>
								</div>
								<div class="card-body py-4">
									<p class="text-muted text-sm">Shipping and additional costs
										applied.</p>
									<table class="table card-text">
										<tbody>
											<tr>
												<th class="py-4">Order Subtotal</th>
												<td class="py-4 text-end text-muted">$${totalAmount}
												</td>
											</tr>
											<tr>
												<th class="py-4">Shipping and handling</th>
												<td class="py-4 text-end text-muted">${shipping}
												</td>
											</tr>
											<tr>
												<th class="py-4">Tax</th>
												<td class="py-4 text-end text-muted">$0.00</td>
											</tr>
											<tr>
												<th class="pt-4 border-0">Estimated Total</th>
												<td class="pt-4 text-end h3 fw-normal border-0">
													$${totalAmount + shipping}
												</td>
											</tr>
										</tbody>
									</table>
								</div>
								<div class="card-footer overflow-hidden p-0">
									<div class="d-grid">
										<form action="/create-checkout-session" method="POST" target="_blank"
											class="text-end">
											<button type="submit" class="checkout btn btn-warning" name="shipping"
												value=${shipping} onclick="handleCheckOut()">Checkout</button>
										</form>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</section>
			<script src="./js/viewcart.js"></script>
		</body>

		</html>