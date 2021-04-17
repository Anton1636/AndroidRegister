using App.Account;
using MediatR;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace App.User.Profile
{
    public class UserListCommand : IRequest<List<UserViewModel>>
    {
        public string UserName { get; set; }
    }
}
