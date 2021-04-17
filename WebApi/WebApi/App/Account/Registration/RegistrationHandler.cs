using Domain;
using App.Exceptions;
using App.Interfaces;
using MediatR;
using Microsoft.AspNetCore.Identity;
using System;
using System.Linq;
using System.Net;
using System.Threading;
using System.Threading.Tasks;
using EFContext;
using Microsoft.EntityFrameworkCore;

namespace App.Account.Registration
{
    public class RegistrationHandler : IRequestHandler<RegistrationCommand, UserViewModel>
	{
		private readonly UserManager<AppUser> _userManager;
		private readonly IJwtGenerator _jwtGenerator;
		private readonly EFDataContext _context;

		public RegistrationHandler(EFDataContext context, UserManager<AppUser> userManager, IJwtGenerator jwtGenerator)
		{
			_context = context;
			_userManager = userManager;
			_jwtGenerator = jwtGenerator;
		}

		public async Task<UserViewModel> Handle(RegistrationCommand request, CancellationToken cancellationToken)
		{
			if (await _context.Users.Where(x => x.Email == request.Email).AnyAsync())
			{
				throw new RestException(HttpStatusCode.BadRequest, new { Email = new[] { "Email already exist" } });
			}

			//if (await _context.Users.Where(x => x.UserName == request.UserName).AnyAsync())
			//{
			//	throw new RestException(HttpStatusCode.BadRequest, new { UserName = "UserName already exist" });
			//}

			var user = new AppUser
			{
				Phone = request.Phone,
				DisplayName = request.DisplayName,
				Email = request.Email,
				UserName = request.Email //request.UserName
			};

			var result = await _userManager.CreateAsync(user, request.Password);

			if (result.Succeeded)
			{
				return new UserViewModel
				{
					DisplayName = user.DisplayName,
					Token = _jwtGenerator.CreateToken(user),
					UserName = user.UserName,
					Image = null,
					Phone = user.Phone
				};
			}

			throw new Exception("Client creation failed");
		}
	}
}
