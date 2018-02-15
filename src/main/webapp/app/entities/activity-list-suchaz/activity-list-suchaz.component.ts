import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { ActivityListSuchaz } from './activity-list-suchaz.model';
import { ActivityListSuchazService } from './activity-list-suchaz.service';
import { Principal } from '../../shared';

@Component({
    selector: 'jhi-activity-list-suchaz',
    templateUrl: './activity-list-suchaz.component.html'
})
export class ActivityListSuchazComponent implements OnInit, OnDestroy {
activityLists: ActivityListSuchaz[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private activityListService: ActivityListSuchazService,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {
    }

    loadAll() {
        this.activityListService.query().subscribe(
            (res: HttpResponse<ActivityListSuchaz[]>) => {
                this.activityLists = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }
    ngOnInit() {
        this.loadAll();
        this.principal.identity().then((account) => {
            this.currentAccount = account;
        });
        this.registerChangeInActivityLists();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: ActivityListSuchaz) {
        return item.id;
    }
    registerChangeInActivityLists() {
        this.eventSubscriber = this.eventManager.subscribe('activityListListModification', (response) => this.loadAll());
    }

    private onError(error) {
        this.jhiAlertService.error(error.message, null, null);
    }
}
