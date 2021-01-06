import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IWeeklyProgram } from 'app/shared/model/weekly-program.model';

type EntityResponseType = HttpResponse<IWeeklyProgram>;
type EntityArrayResponseType = HttpResponse<IWeeklyProgram[]>;

@Injectable({ providedIn: 'root' })
export class WeeklyProgramService {
  public resourceUrl = SERVER_API_URL + 'api/weekly-programs';

  constructor(protected http: HttpClient) {}

  create(weeklyProgram: IWeeklyProgram): Observable<EntityResponseType> {
    return this.http.post<IWeeklyProgram>(this.resourceUrl, weeklyProgram, { observe: 'response' });
  }

  update(weeklyProgram: IWeeklyProgram): Observable<EntityResponseType> {
    return this.http.put<IWeeklyProgram>(this.resourceUrl, weeklyProgram, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IWeeklyProgram>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IWeeklyProgram[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
