package com.lambdaschool.nbapredictor.controllers;

import com.lambdaschool.nbapredictor.logging.Loggable;
import com.lambdaschool.nbapredictor.models.User;
import com.lambdaschool.nbapredictor.models.UserMinimum;
import com.lambdaschool.nbapredictor.models.UserRoles;
import com.lambdaschool.nbapredictor.services.RoleService;
import com.lambdaschool.nbapredictor.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

@Loggable
@RestController
public class UserOnboardingController {

	private static final Logger logger = LoggerFactory.getLogger(UserOnboardingController.class);

	@Autowired
	private UserService userService;

	@Autowired
	private RoleService roleService;

	@Autowired
	private TokenStore tokenStore;

	@PostMapping(value = "/signup",
			consumes = {"application/json"},
			produces = {"application/json"})
	public ResponseEntity<?> addNewUser(HttpServletRequest httpServletRequest,
										@RequestParam(defaultValue = "true")
												boolean getaccess,
										@Valid
										@RequestBody UserMinimum newminuser) throws URISyntaxException
	{
		logger.trace(httpServletRequest.getMethod()
				.toUpperCase() + " " + httpServletRequest.getRequestURI() + " accessed");

		// Create the user
		User newuser = new User();

		newuser.setUsername(newminuser.getUsername());
		newuser.setPassword(newminuser.getPassword());
		newuser.setPrimaryemail(newminuser.getPrimaryemail());

		ArrayList<UserRoles> newRoles = new ArrayList<>();
		newRoles.add(new UserRoles(newuser,
				roleService.findByName("user")));
		newuser.setUserroles(newRoles);

		newuser = userService.save(newuser);

		// set the location header for the newly created resource - to another controller!
		HttpHeaders responseHeaders = new HttpHeaders();
		URI newUserURI = ServletUriComponentsBuilder.fromUriString(httpServletRequest.getServerName() + ":" + httpServletRequest.getLocalPort() + "/users/user/{userId}")
				.buildAndExpand(newuser.getUserid())
				.toUri();
		responseHeaders.setLocation(newUserURI);

		String theToken = "";
		if (getaccess)
		{
			// return the access token
			RestTemplate restTemplate = new RestTemplate();
			String requestURI = "http://" + httpServletRequest.getServerName() + ":" + httpServletRequest.getLocalPort() + "/login";

			List<MediaType> acceptableMediaTypes = new ArrayList<>();
			acceptableMediaTypes.add(MediaType.APPLICATION_JSON);

			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
			headers.setAccept(acceptableMediaTypes);
			headers.setBasicAuth(System.getenv("OAUTHCLIENTID"),
					System.getenv("OAUTHCLIENTSECRET"));

			MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
			map.add("grant_type",
					"password");
			map.add("scope",
					"read write trust");
			map.add("username",
					newminuser.getUsername());
			map.add("password",
					newminuser.getPassword());

			HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map,
					headers);

			theToken = restTemplate.postForObject(requestURI,
					request,
					String.class);
		} else
		{
			// nothing;
		}
		return new ResponseEntity<>(theToken,
				responseHeaders,
				HttpStatus.CREATED);
	}

	@RequestMapping(value = "/logout",
			method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public void logout(HttpServletRequest request)
	{
		logger.trace(request.getMethod()
				.toUpperCase() + " " + request.getRequestURI() + " accessed");

		String authHeader = request.getHeader("Authorization");
		if (authHeader != null)
		{
			String tokenValue = authHeader.replace("Bearer",
					"")
					.trim();
			OAuth2AccessToken accessToken = tokenStore.readAccessToken(tokenValue);
			tokenStore.removeAccessToken(accessToken);
		}
	}
}
