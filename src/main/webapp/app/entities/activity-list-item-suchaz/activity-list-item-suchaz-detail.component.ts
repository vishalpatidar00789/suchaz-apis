import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager } from 'ng-jhipster';

import { ActivityListItemSuchaz } from './activity-list-item-suchaz.model';
import { ActivityListItemSuchazService } from './activity-list-item-suchaz.service';

@Component({
    selector: 'jhi-activity-list-item-suchaz-detail',
    templateUrl: './activity-list-item-suchaz-detail.component.html'
})
export class ActivityListItemSuchazDetailComponent implements OnInit, OnDestroy {

    activityListItem: ActivityListItemSuchaz;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private activityListItemService: ActivityListItemSuchazService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInActivityListItems();
    }

    load(id) {
        this.activityListItemService.find(id)
            .subscribe((activityListItemResponse: HttpResponse<ActivityListItemSuchaz>) => {
                this.activityListItem = activityListItemResponse.body;
            });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInActivityListItems() {
        this.eventSubscriber = this.eventManager.subscribe(
            'activityListItemListModification',
            (response) => this.load(this.activityListItem.id)
        );
    }
}
