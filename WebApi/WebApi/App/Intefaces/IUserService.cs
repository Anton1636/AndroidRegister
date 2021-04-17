using App.Account;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace App.Interfaces
{
    public interface IUserService
    {
        UserViewModel ChageUserImage(string userName, string image);
    }
}
