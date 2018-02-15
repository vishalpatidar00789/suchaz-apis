import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { SERVER_API_URL } from '../../app.constants';

import { ActivityListSuchaz } from './activity-list-suchaz.model';
import { createRequestOption } from '../../shared';

export type EntityResponseType = HttpResponse<ActivityListSuchaz>;

@Injectable()
export class ActivityListSuchazService {

    private resourceUrl =  SERVER_API_URL + 'api/activity-lists';

    constructor(private http: HttpClient) { }

    create(activityList: ActivityListSuchaz): Observable<EntityResponseType> {
        const copy = this.convert(activityList);
        return this.http.post<ActivityListSuchaz>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    update(activityList: ActivityListSuchaz): Observable<EntityResponseType> {
        const copy = this.convert(activityList);
        return this.http.put<ActivityListSuchaz>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<ActivityListSuchaz>(`${this.resourceUrl}/${id}`, { observe: 'response'})
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    query(req?: any): Observable<HttpResponse<ActivityListSuchaz[]>> {
        const options = createRequestOption(req);
        return this.http.get<ActivityListSuchaz[]>(this.resourceUrl, { params: options, observe: 'response' })
            .map((res: HttpResponse<ActivityListSuchaz[]>) => this.convertArrayResponse(res));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response'});
    }

    private convertResponse(res: EntityResponseType): EntityResponseType {
        const body: ActivityListSuchaz = this.convertItemFromServer(res.body);
        return res.clone({body});
    }

    private convertArrayResponse(res: HttpResponse<ActivityListSuchaz[]>): HttpResponse<ActivityListSuchaz[]> {
        const jsonResponse: ActivityListSuchaz[] = res.body;
        const body: ActivityListSuchaz[] = [];
        for (let i = 0; i < jsonResponse.length; i++) {
            body.push(this.convertItemFromServer(jsonResponse[i]));
        }
        return res.clone({body});
    }

    /**
     * Convert a returned JSON object to ActivityListSuchaz.
     */
    private convertItemFromServer(activityList: ActivityListSuchaz): ActivityListSuchaz {
        const copy: ActivityListSuchaz = Object.assign({}, activityList);
        return copy;
    }

    /**
     * Convert a ActivityListSuchaz to a JSON which can be sent to the server.
     */
    private convert(activityList: ActivityListSuchaz): ActivityListSuchaz {
        const copy: ActivityListSuchaz = Object.assign({}, activityList);
        return copy;
    }
}
