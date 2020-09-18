using System;
using System.Collections.Generic;
using System.Data;
using System.Data.SqlClient;
using System.Linq;
using System.Web;

namespace WebAppDongHo.Models
{
    public class SanPhamRepository : BaseRepository
    {
        public List<Term> SearchSanPham(string q)
        {
            using (IDbConnection connection = new SqlConnection(connectionString))
            {
                using (IDbCommand command = connection.CreateCommand())
                {
                    command.CommandText = "SELECT * FROM SANPHAM WHERE TenSP LIKE '%' + '" + q + "' + '%'";
                    connection.Open();
                    using (IDataReader reader = command.ExecuteReader())
                    {
                        List<Term> list = new List<Term>();
                        while (reader.Read())
                        {
                            list.Add(new Term
                            {
                                Id = (int)reader["MaSP"],
                                Label = (string)reader["TenSP"],
                                Value = (string)reader["TenSP"]
                            });
                        }
                        return list;
                    }
                }
            }
        }
    }
}