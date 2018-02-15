import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager } from 'ng-jhipster';

import { ActivityListSuchaz } from './activity-list-suchaz.model';
import { ActivityListSuchazService } from './activity-list-suchaz.service';

@Component({
    selector: 'jhi-activity-list-suchaz-detail',
    templateUrl: './activity-list-suchaz-detail.component.html'
})
export class ActivityListSuchazDetailComponent implements OnInit, OnDestroy {

    activityList: ActivityListSuchaz;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private activityListService: ActivityListSuchazService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInActivityLists();
    }

    load(id) {
        this.activityListService.find(id)
            .subscribe((activityListResponse: HttpResponse<ActivityListSuchaz>) => {
                this.activityList = activityListResponse.body;
            });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInActivityLists() {
        this.eventSubscriber = this.eventManager.subscribe(
            'activityListListModification',
            (response) => this.load(this.activityList.id)
        );
    }
}
