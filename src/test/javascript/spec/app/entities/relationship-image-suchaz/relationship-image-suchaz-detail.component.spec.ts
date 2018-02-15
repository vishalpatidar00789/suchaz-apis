/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';

import { SuchazapisTestModule } from '../../../test.module';
import { RelationshipImageSuchazDetailComponent } from '../../../../../../main/webapp/app/entities/relationship-image-suchaz/relationship-image-suchaz-detail.component';
import { RelationshipImageSuchazService } from '../../../../../../main/webapp/app/entities/relationship-image-suchaz/relationship-image-suchaz.service';
import { RelationshipImageSuchaz } from '../../../../../../main/webapp/app/entities/relationship-image-suchaz/relationship-image-suchaz.model';

describe('Component Tests', () => {

    describe('RelationshipImageSuchaz Management Detail Component', () => {
        let comp: RelationshipImageSuchazDetailComponent;
        let fixture: ComponentFixture<RelationshipImageSuchazDetailComponent>;
        let service: RelationshipImageSuchazService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [SuchazapisTestModule],
                declarations: [RelationshipImageSuchazDetailComponent],
                providers: [
                    RelationshipImageSuchazService
                ]
            })
            .overrideTemplate(RelationshipImageSuchazDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(RelationshipImageSuchazDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(RelationshipImageSuchazService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                spyOn(service, 'find').and.returnValue(Observable.of(new HttpResponse({
                    body: new RelationshipImageSuchaz(123)
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.find).toHaveBeenCalledWith(123);
                expect(comp.relationshipImage).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
