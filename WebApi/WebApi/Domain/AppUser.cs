using Microsoft.AspNetCore.Identity;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Domain
{
    public class AppUser : IdentityUser<long>
    {
        public string DisplayName { get; set; }
        public string Phone { get; set; }
        public string Image { get; set; }
        public virtual ICollection<AppUserRole> UserRoles { get; set; }
    }
}
