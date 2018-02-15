import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager, JhiDataUtils } from 'ng-jhipster';

import { OccasionImageSuchaz } from './occasion-image-suchaz.model';
import { OccasionImageSuchazService } from './occasion-image-suchaz.service';

@Component({
    selector: 'jhi-occasion-image-suchaz-detail',
    templateUrl: './occasion-image-suchaz-detail.component.html'
})
export class OccasionImageSuchazDetailComponent implements OnInit, OnDestroy {

    occasionImage: OccasionImageSuchaz;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private dataUtils: JhiDataUtils,
        private occasionImageService: OccasionImageSuchazService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInOccasionImages();
    }

    load(id) {
        this.occasionImageService.find(id)
            .subscribe((occasionImageResponse: HttpResponse<OccasionImageSuchaz>) => {
                this.occasionImage = occasionImageResponse.body;
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

    registerChangeInOccasionImages() {
        this.eventSubscriber = this.eventManager.subscribe(
            'occasionImageListModification',
            (response) => this.load(this.occasionImage.id)
        );
    }
}
