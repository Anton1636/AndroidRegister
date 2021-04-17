using Domain;
using App.Account;
using App.Exceptions;
using MediatR;
using Microsoft.AspNetCore.Identity;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Text;
using System.Threading;
using System.Threading.Tasks;

namespace App.User.Profile
{
    public class UserProfileHandler : IRequestHandler<UserProfileCommand, UserViewModel>
    {
        private readonly UserManager<AppUser> _userManager;
        public UserProfileHandler(UserManager<AppUser> userManager)
        {
            _userManager = userManager;
        }
        public async Task<UserViewModel> Handle(UserProfileCommand request, CancellationToken cancellationToken)
        {
            var user = await _userManager.FindByNameAsync(request.UserName);
            if (user == null)
            {
                throw new RestException(HttpStatusCode.NotFound);
            }

            return new UserViewModel
            {
                Phone = user.Phone,
                DisplayName = user.DisplayName,
                Image = "profile.jpg",
                UserName = user.UserName,
                Email = user.UserName
            };
            //throw new RestException(HttpStatusCode.NotFound);
        }
    }
}
