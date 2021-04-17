﻿using Domain;
using App.Account;
using App.Exceptions;
using App.Interfaces;
using MediatR;
using Microsoft.AspNetCore.Identity;
using System;
using System.Collections.Generic;
using System.Drawing;
using System.IO;
using System.Linq;
using System.Net;
using System.Text;
using System.Threading;
using System.Threading.Tasks;

namespace App.User.Profile
{
    public class UserUploadImageHandler : IRequestHandler<UserUploadImageCommand,UserViewModel>
    {
        private readonly IUserService _userService;
        public UserUploadImageHandler(IUserService userService)
        {
            _userService = userService;
        }
        public async Task<UserViewModel> Handle(UserUploadImageCommand request, 
            CancellationToken cancellationToken)
        {
            return _userService.ChageUserImage(request.UserName, request.Image);
        }
    }
}
