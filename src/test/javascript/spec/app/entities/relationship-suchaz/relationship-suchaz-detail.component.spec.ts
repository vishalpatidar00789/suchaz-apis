/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';

import { SuchazapisTestModule } from '../../../test.module';
import { RelationshipSuchazDetailComponent } from '../../../../../../main/webapp/app/entities/relationship-suchaz/relationship-suchaz-detail.component';
import { RelationshipSuchazService } from '../../../../../../main/webapp/app/entities/relationship-suchaz/relationship-suchaz.service';
import { RelationshipSuchaz } from '../../../../../../main/webapp/app/entities/relationship-suchaz/relationship-suchaz.model';

describe('Component Tests', () => {

    describe('RelationshipSuchaz Management Detail Component', () => {
        let comp: RelationshipSuchazDetailComponent;
        let fixture: ComponentFixture<RelationshipSuchazDetailComponent>;
        let service: RelationshipSuchazService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [SuchazapisTestModule],
                declarations: [RelationshipSuchazDetailComponent],
                providers: [
                    RelationshipSuchazService
                ]
            })
            .overrideTemplate(RelationshipSuchazDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(RelationshipSuchazDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(RelationshipSuchazService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                spyOn(service, 'find').and.returnValue(Observable.of(new HttpResponse({
                    body: new RelationshipSuchaz(123)
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.find).toHaveBeenCalledWith(123);
                expect(comp.relationship).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
