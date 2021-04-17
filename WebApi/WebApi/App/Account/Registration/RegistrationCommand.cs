using MediatR;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace App.Account.Registration
{
	public class RegistrationCommand : IRequest<UserViewModel>
	{
		public string DisplayName { get; set; }

		public string Phone { get; set; }

		//public string UserName { get; set; }

		public string Email { get; set; }

		public string Password { get; set; }
	}
}
