import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager, JhiDataUtils } from 'ng-jhipster';

import { RelationshipImageSuchaz } from './relationship-image-suchaz.model';
import { RelationshipImageSuchazService } from './relationship-image-suchaz.service';

@Component({
    selector: 'jhi-relationship-image-suchaz-detail',
    templateUrl: './relationship-image-suchaz-detail.component.html'
})
export class RelationshipImageSuchazDetailComponent implements OnInit, OnDestroy {

    relationshipImage: RelationshipImageSuchaz;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private dataUtils: JhiDataUtils,
        private relationshipImageService: RelationshipImageSuchazService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInRelationshipImages();
    }

    load(id) {
        this.relationshipImageService.find(id)
            .subscribe((relationshipImageResponse: HttpResponse<RelationshipImageSuchaz>) => {
                this.relationshipImage = relationshipImageResponse.body;
            });
    }
    byteSize(field) {
        return this.dataUtils.byteSize(field);
    }

    openFile(contentType, field) {
        return this.dataUtils.openFile(contentType, field);
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInRelationshipImages() {
        this.eventSubscriber = this.eventManager.subscribe(
            'relationshipImageListModification',
            (response) => this.load(this.relationshipImage.id)
        );
    }
}
