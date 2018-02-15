import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { ActivityListItemSuchaz } from './activity-list-item-suchaz.model';
import { ActivityListItemSuchazService } from './activity-list-item-suchaz.service';
import { Principal } from '../../shared';

@Component({
    selector: 'jhi-activity-list-item-suchaz',
    templateUrl: './activity-list-item-suchaz.component.html'
})
export class ActivityListItemSuchazComponent implements OnInit, OnDestroy {
activityListItems: ActivityListItemSuchaz[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private activityListItemService: ActivityListItemSuchazService,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {
    }

    loadAll() {
        this.activityListItemService.query().subscribe(
            (res: HttpResponse<ActivityListItemSuchaz[]>) => {
                this.activityListItems = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }
    ngOnInit() {
        this.loadAll();
        this.principal.identity().then((account) => {
            this.currentAccount = account;
        });
        this.registerChangeInActivityListItems();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: ActivityListItemSuchaz) {
        return item.id;
    }
    registerChangeInActivityListItems() {
        this.eventSubscriber = this.eventManager.subscribe('activityListItemListModification', (response) => this.loadAll());
    }

    private onError(error) {
        this.jhiAlertService.error(error.message, null, null);
    }
}
