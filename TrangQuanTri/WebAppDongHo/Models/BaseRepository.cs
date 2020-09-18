using System;
using System.Collections.Generic;
using System.Configuration;
using System.Data;
using System.Data.SqlClient;
using System.Linq;
using System.Web;

namespace WebAppDongHo.Models
{
    public abstract class BaseRepository
    {
        protected static string connectionString = ConfigurationManager.ConnectionStrings["DonghoConnectionString"].ConnectionString;

        protected static void SetParameter(IDbCommand command, Parameter parameter)
        {
            IDbDataParameter dataParameter = command.CreateParameter();
            dataParameter.ParameterName = parameter.Name;
            dataParameter.Value = parameter.Value;
            dataParameter.DbType = parameter.DbType;
            if (Enum.IsDefined(typeof(ParameterDirection), parameter.Direction))
            {
                dataParameter.Direction = parameter.Direction;
            }
            command.Parameters.Add(dataParameter);
        }
        protected static void SetParameter(IDbCommand command, Parameter[] parameters)
        {
            foreach (var parameter in parameters)
            {
                SetParameter(command, parameter);
            }
        }
        protected int Save(string sql, Parameter[] parameters)
        {
            using (IDbConnection connection = new SqlConnection(connectionString))
            {
                using (IDbCommand command = connection.CreateCommand())
                {
                    command.CommandText = sql;
                    command.CommandType = CommandType.StoredProcedure;
                    SetParameter(command, parameters);
                    connection.Open();
                    return command.ExecuteNonQuery();
                }
            }
        }
        protected int Save(string sql, Parameter parameter)
        {
            return Save(sql, new Parameter[] { parameter });
        }
    }
}